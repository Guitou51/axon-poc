package com.example.axontest.commmand;

import com.example.axontest.coreapi.command.AddOperationCommand;
import com.example.axontest.coreapi.command.CreateAccountCommand;
import com.example.axontest.coreapi.event.AccountCreatedEvent;
import com.example.axontest.coreapi.event.OperationAddedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Aggregate
@Slf4j
public class Account {
    @AggregateIdentifier
    private UUID accountId;
    private BigDecimal balance;
    private List<Money> operations;

    public Account() {
        log.info("new Account");
    }

    @CommandHandler
    public Account(CreateAccountCommand createAccountCommand) {
        log.info("new Account: {}", createAccountCommand);
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.getAccountId()));
    }

    @CommandHandler
    public void handle(AddOperationCommand addOperationCommand) {
        log.info("handle: {}", addOperationCommand);
        BigDecimal money = addOperationCommand.getMoney();
        BigDecimal a = balance.add(money);
        if (a.doubleValue() < 0) {
            throw new RuntimeException("negative balance");
        }
        AggregateLifecycle.apply(new OperationAddedEvent(accountId, a, addOperationCommand.getCurrency()));

    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        log.info("on: {}", event);
        accountId = event.getAccountId();
        balance = BigDecimal.ZERO;
        operations = new ArrayList<>();
    }

    @EventSourcingHandler
    public void on(OperationAddedEvent event) {
        log.info("on: {}", event);
        accountId = event.getAccountId();
        balance = event.getMoney();
        operations.add(Money.of(balance, event.getCurrency().getCurrencyCode()));
    }
}