package com.example.axontest.commmand;

import com.example.axontest.coreapi.command.AddOperationCommand;
import com.example.axontest.coreapi.command.CreateAccountCommand;
import com.example.axontest.coreapi.event.AccountCreatedEvent;
import com.example.axontest.coreapi.event.OperationAddedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.axonframework.test.aggregate.StubAggregateLifecycleExtension;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

import static org.axonframework.test.matchers.Matchers.andNoMore;
import static org.axonframework.test.matchers.Matchers.exactSequenceOf;
import static org.axonframework.test.matchers.Matchers.messageWithPayload;

class AccountTest {
    @RegisterExtension
    static StubAggregateLifecycleExtension testSubject = new StubAggregateLifecycleExtension();

    private FixtureConfiguration<Account> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(Account.class);
    }

    @Test
    void create() {
        fixture.givenNoPriorActivity()
                .when(new CreateAccountCommand(UUID.randomUUID()))
                .expectEventsMatching(exactSequenceOf(
                        messageWithPayload(Matchers.both(Matchers.isA(AccountCreatedEvent.class)).and(Matchers.hasProperty("accountId", Matchers.notNullValue()))),
                        andNoMore()
                ));

    }

    @Test
    void handle() {
        UUID accountId = UUID.randomUUID();
        fixture.givenCommands(new CreateAccountCommand(accountId))
                .when(new AddOperationCommand(accountId, BigDecimal.TEN, Currency.getInstance("EUR")))
                .expectEvents(new OperationAddedEvent(accountId, BigDecimal.TEN, Currency.getInstance("EUR")));
    }

    @Test
    void on() {
    }

    @Test
    void testOn() {
    }
}