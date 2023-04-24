package ru.rikmasters.test.driverservice.records;

import java.util.UUID;

public record TransactionRequest(UUID accountId, Double amount) {
}
