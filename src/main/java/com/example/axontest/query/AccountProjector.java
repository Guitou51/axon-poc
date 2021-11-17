package com.example.axontest.query;

import com.example.axontest.coreapi.event.AccountCreatedEvent;
import com.example.axontest.coreapi.event.OperationAddedEvent;
import com.example.axontest.coreapi.query.FindAccountQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountProjector {
    private final AccountViewRepo accountViewRepo;

    @EventHandler
    public void on(AccountCreatedEvent event) {
        log.info("on AccountCreatedEvent");
        AccountView accountView = new AccountView(event.getAccountId(), Money.of(0, "EUR"));
        accountViewRepo.save(accountView);
    }

    @EventHandler
    public void on(OperationAddedEvent event) {
        log.info("on OperationAddedEvent");
        accountViewRepo.findById(event.getAccountId()).ifPresent(entity -> {
            entity.setBalance(Money.of(event.getMoney(), entity.getBalance().getCurrency()));
            accountViewRepo.save(entity);
        });

    }

    @QueryHandler
    public AccountView handle(FindAccountQuery query) {
        return accountViewRepo.findById(query.getAccountId()).orElse(null);
    }
}
