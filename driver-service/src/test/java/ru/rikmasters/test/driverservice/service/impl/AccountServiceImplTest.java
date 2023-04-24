package ru.rikmasters.test.driverservice.service.impl;

import common.exception.DriverException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rikmasters.test.driverservice.dto.AccountDto;
import ru.rikmasters.test.driverservice.entity.Account;
import ru.rikmasters.test.driverservice.entity.Driver;
import ru.rikmasters.test.driverservice.enums.Currency;
import ru.rikmasters.test.driverservice.records.TransactionRequest;
import ru.rikmasters.test.driverservice.repository.AccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.rikmasters.test.driverservice.utils.TestUtils.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    AccountRepository repository;
    @InjectMocks
    AccountServiceImpl service;

    List<Account> accounts = makeAccounts();
    List<AccountDto> accountDtos = makeAccountsDto();
    Driver driver = makeDriver();
    UUID redAccountId = accounts.get(0).getId();
    UUID greenAccountId = accounts.get(2).getId();

    @Test
    @DisplayName("Получение коллекции счетов по ID водителя")
    void testFindAccountsByOwner_ShouldReturnThreeAccounts() {
        when(repository.findAllByOwnerId(driver.getId())).thenReturn(accounts);

        var result = service.findAllAccountsByOwner(driver.getId(), null);
        assertEquals(3, result.size());
        assertEquals(accounts, result);
    }

    @Test
    @DisplayName("Получение одного счета по ID водителя")
    void testFindAccountsByOwner_ShouldReturnOneAccount() {
        when(repository.findAllByOwnerId(driver.getId())).thenReturn(accounts);
        var result = service.findAllAccountsByOwner(driver.getId(), Currency.GREEN);

        assertEquals(1, result.size());
        assertEquals(Currency.GREEN, result.get(0).getCurrency());
        assertNotEquals(accounts, result);
    }

    @Test
    @DisplayName("Получение коллекции по ID водителя - исключение счет не найден")
    void testFindAccountsByOwner_ShouldThrowNotFound() {
        when(repository.findAllByOwnerId(driver.getId())).thenReturn(List.of());

        DriverException result = assertThrows(DriverException.class, () -> service.findAllAccountsByOwner(driver.getId(), Currency.GREEN));
        assertEquals("Не найдено ни одного счета", result.getMessage());
    }

    @Test
    @DisplayName("Начисление средств водителю")
    void testDoTransaction_ShouldCallSaveAndNoThrow() {
        var request = new TransactionRequest(redAccountId, 50d);
        when(repository.findById(redAccountId)).thenReturn(Optional.of(accounts.get(0)));

        assertDoesNotThrow(() -> service.doTransaction(request.accountId(), request.amount()));

        verify(repository).save(accounts.get(0));
    }

    @Test
    @DisplayName("Начисление средств водителю - исключение отрицательный баланс")
    void testDoTransaction_ShouldThrowWhenBalanceNegative() {
        var request = new TransactionRequest(redAccountId, -101d);
        when(repository.findById(redAccountId)).thenReturn(Optional.of(accounts.get(0)));

        assertThrows(DriverException.class, () -> service.doTransaction(request.accountId(), request.amount()));
    }

    @Test
    @DisplayName("Удаление счета")
    void testDeleteAccount_ShouldNotThrowAndCallRepository() {
        when(repository.findById(greenAccountId)).thenReturn(Optional.of(accounts.get(2)));

        service.deleteAccount(greenAccountId);

        verify(repository).deleteById(greenAccountId);
    }

    @Test
    @DisplayName("Удаление счета - исключение ненулевой баланс счета")
    void testDeleteAccount_ShouldThrowDriverException() {
        when(repository.findById(redAccountId)).thenReturn(Optional.of(accounts.get(0)));

        DriverException result = assertThrows(DriverException.class, () -> service.deleteAccount(redAccountId));

        assertEquals("Невозможно удалить счет с ненулевым балансом", result.getMessage());
    }

    @Test
    @DisplayName("Поиск счета по ID")
    void testFindById_ShouldReturnAccount() {
        when(repository.findById(redAccountId)).thenReturn(Optional.of(accounts.get(0)));

        var result = service.findById(redAccountId);

        assertEquals(redAccountId, result.getId());
        assertEquals(Currency.RED, result.getCurrency());
        assertEquals(100d, result.getBalance());
    }

    @Test
    @DisplayName("Поиск счета по ID - исключение счет не найден")
    void testFindById_ShouldThrowNotFound() {
        when(repository.findById(redAccountId)).thenReturn(Optional.empty());

        DriverException result = assertThrows(DriverException.class, () -> service.findById(redAccountId));

        assertEquals("Счет не найден", result.getMessage());
    }
}
