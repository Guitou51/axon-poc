package com.example.axontest.coreapi.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@ToString
public final class CreateAccountCommand {
    private final UUID accountId;
}
