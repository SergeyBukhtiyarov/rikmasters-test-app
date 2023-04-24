package ru.rikmasters.test.driverservice.service.impl;

import common.exception.DriverException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.rikmasters.test.driverservice.entity.Account;
import ru.rikmasters.test.driverservice.enums.Currency;
import ru.rikmasters.test.driverservice.repository.AccountRepository;
import ru.rikmasters.test.driverservice.service.AccountService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    public List<Account> findAllAccountsByOwner(UUID ownerId, @Nullable Currency currency) {

        List<Account> result = repository
                .findAllByOwnerId(ownerId)
                .stream()
                .filter(account -> {
                    if (currency != null) {
                        return account.getCurrency() == currency;
                    } else
                        return true;
                })
                .toList();

        if (result.isEmpty()) {
            throw new DriverException("Не найдено ни одного счета");
        }
        return result;
    }

    @Transactional
    public void doTransaction(UUID accountId, Double amount) {
        Account account = findById(accountId);
        Double resultBalance = account.getBalance() + amount;

        if (resultBalance < 0) {
            throw new DriverException("На счете недостаточно средств");
        } else {
            account.setBalance(resultBalance);
            repository.save(account);
        }
    }

    public void deleteAccount(UUID accountId) {
        Account account = findById(accountId);
        if (account.getBalance() != 0) {
            throw new DriverException("Невозможно удалить счет с ненулевым балансом");
        } else {
            repository.deleteById(accountId);
        }
    }

    public Account findById(UUID accountId) {
        return repository.findById(accountId).orElseThrow(() -> new DriverException("Счет не найден"));
    }
}
