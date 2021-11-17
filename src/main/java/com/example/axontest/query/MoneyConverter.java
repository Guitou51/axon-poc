package com.example.axontest.query;

import org.javamoney.moneta.Money;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;

@Converter
public class MoneyConverter implements AttributeConverter<Money, String> {

    @Override
    public String convertToDatabaseColumn(Money money) {
        return money.toString();
    }

    @Override
    public Money convertToEntityAttribute(String money) {
        return Money.parse(money);
    }
}
