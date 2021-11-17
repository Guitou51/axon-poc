package com.example.axontest.controller;

import com.example.axontest.coreapi.command.AddOperationCommand;
import com.example.axontest.coreapi.command.CreateAccountCommand;
import com.example.axontest.coreapi.query.FindAccountQuery;
import com.example.axontest.query.AccountView;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Currency;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class AccountController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping
    public UUID createAccount() {
        return commandGateway.sendAndWait(new CreateAccountCommand(UUID.randomUUID()));
    }

    @GetMapping("/{uuid}")
    public CompletableFuture<AccountView> findFoodCart(@PathVariable("uuid") String uuid) {
        return queryGateway.query(
                new FindAccountQuery(UUID.fromString(uuid)),
                ResponseTypes.instanceOf(AccountView.class)
        );
    }

    @PostMapping("operation/{uuid}")
    public ResponseEntity<Void> addOperation(@PathVariable UUID uuid, @RequestBody BigDecimal amount) {
        commandGateway.sendAndWait(new AddOperationCommand(uuid, amount, Currency.getInstance("EUR")));
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "stream", produces = "application/x-ndjson")
    Mono<ZonedDateTime> stream() {
        return Mono.<ZonedDateTime>create(sink -> sink.success(ZonedDateTime.now())).log();
    }

    @GetMapping(value = "flux", produces = "application/x-ndjson")
    Flux<ZonedDateTime> flux() {
        return Flux.interval(Duration.ofMillis(500)).map(i -> ZonedDateTime.now()).take(10).log();
    }
}
