package ru.rikmasters.test.driverservice.dto;

import lombok.Data;
import ru.rikmasters.test.driverservice.entity.Driver;
import ru.rikmasters.test.driverservice.enums.Currency;

import java.util.UUID;

@Data
public class AccountDto {
    private UUID id;
    private Currency currency;
    private Double amount;
    private DriverDto owner;
}
