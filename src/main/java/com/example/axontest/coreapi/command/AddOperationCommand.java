package com.example.axontest.coreapi.command;

import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

@Getter
@ToString
public final class AddOperationCommand {
    @TargetAggregateIdentifier
    private final UUID accountId;
    private final BigDecimal money;
    private final Currency currency;

    public AddOperationCommand(UUID accountId, BigDecimal money, Currency currency) {
        this.accountId = accountId;
        this.money = money;
        this.currency = currency;
    }

}
