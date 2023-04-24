package ru.rikmasters.test.driverservice.service;

import org.springframework.lang.Nullable;
import ru.rikmasters.test.driverservice.entity.Account;
import ru.rikmasters.test.driverservice.enums.Currency;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    List<Account> findAllAccountsByOwner(UUID ownerId, @Nullable Currency currency);

    void doTransaction(UUID accountId, Double amount);

    void deleteAccount(UUID accountId);
}
