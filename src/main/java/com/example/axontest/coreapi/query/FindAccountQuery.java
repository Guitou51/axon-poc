package com.example.axontest.coreapi.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class FindAccountQuery {
    private UUID accountId;
}
