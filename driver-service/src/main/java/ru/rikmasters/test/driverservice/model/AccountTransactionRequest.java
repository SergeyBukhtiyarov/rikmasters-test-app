package ru.rikmasters.test.driverservice.model;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountTransactionRequest {
    private UUID accountId;
    private Double amount;
}
