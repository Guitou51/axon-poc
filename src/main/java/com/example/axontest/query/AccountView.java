package com.example.axontest.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javamoney.moneta.Money;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountView {
    @Id
    @Column(nullable = false)
    private UUID id;

    @Convert(converter = MoneyConverter.class)
    private Money balance;

    public void addOperation(Money money) {
        balance = balance.add(money);
    }
}
