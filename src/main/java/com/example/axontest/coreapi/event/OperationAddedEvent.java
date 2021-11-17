package com.example.axontest.coreapi.event;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

@RequiredArgsConstructor
@Value
@ToString
public class OperationAddedEvent {
    UUID accountId;
    BigDecimal money;
    Currency currency;
}
