package com.example.axontest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.zalando.jackson.datatype.money.MoneyModule;

@SpringBootApplication
public class AxonTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonTestApplication.class, args);
    }

    @Bean
    public MoneyModule moneyModule() {
        return new MoneyModule();
    }
}
