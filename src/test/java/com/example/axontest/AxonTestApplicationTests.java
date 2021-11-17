package com.example.axontest;

import com.example.axontest.coreapi.command.AddOperationCommand;
import com.example.axontest.coreapi.command.CreateAccountCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Currency;
import java.util.UUID;

@SpringBootTest
class AxonTestApplicationTests {
    @Test
    void contextLoads() {

    }

}
