package ru.rikmasters.test.driverservice.controller;

import common.exception.DriverException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.rikmasters.test.driverservice.dto.AccountDto;
import ru.rikmasters.test.driverservice.entity.Account;
import ru.rikmasters.test.driverservice.mapper.AccountMapper;
import ru.rikmasters.test.driverservice.records.TransactionRequest;
import ru.rikmasters.test.driverservice.repository.AccountRepository;
import ru.rikmasters.test.driverservice.service.AccountService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.rikmasters.test.driverservice.utils.TestUtils.*;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AccountControllerTest {

    public static final String BASE_URL = "/drivers/account";

    @Autowired
    MockMvc mockMvc;
    @MockBean
    AccountMapper mapper;
    @MockBean
    AccountRepository repository;
    @MockBean
    AccountService service;

    List<Account> accounts = makeAccounts();
    List<AccountDto> accountDtos = makeAccountsDto();

    @Test
    @SneakyThrows
    void testFindAll_ShouldReturnCollectionOfDriver() {
        Page<Account> page = new PageImpl<>(accounts);
        when(mapper.asDTO(accounts.get(0))).thenReturn(accountDtos.get(0));
        when(mapper.asDTO(accounts.get(1))).thenReturn(accountDtos.get(1));
        when(mapper.asDTO(accounts.get(2))).thenReturn(accountDtos.get(2));
        when(repository.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get(BASE_URL))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value("00000000-0000-0000-0000-00000000000a"))
                .andExpect(jsonPath("$[0].currency").value("RED"))
                .andExpect(jsonPath("$[0].amount").value("100.0"))
                .andExpect(jsonPath("$[0].owner.id").value("00000000-0000-0000-0000-000000000000"))
                .andExpect(jsonPath("$[1].id").value("00000000-0000-0000-0000-00000000000b"))
                .andExpect(jsonPath("$[1].currency").value("GREEN"))
                .andExpect(jsonPath("$[1].amount").value("200.0"))
                .andExpect(jsonPath("$[1].owner.id").value("00000000-0000-0000-0000-000000000000"))
                .andExpect(jsonPath("$[2].id").value("00000000-0000-0000-0000-00000000000c"))
                .andExpect(jsonPath("$[2].currency").value("BLUE"))
                .andExpect(jsonPath("$[2].amount").value("0.0"))
                .andExpect(jsonPath("$[2].owner.id").value("00000000-0000-0000-0000-000000000000"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void testFindById_ShouldReturnOneDriver() {
        when(mapper.asDTO(any())).thenReturn(accountDtos.get(0));
        when(repository.findById(any())).thenReturn(Optional.of(accounts.get(0)));

        mockMvc.perform(get(BASE_URL + "/{id}", accounts.get(0).getId()))
                .andExpect(jsonPath("$.id").value("00000000-0000-0000-0000-00000000000a"))
                .andExpect(jsonPath("$.currency").value("RED"))
                .andExpect(jsonPath("$.amount").value("100.0"))
                .andExpect(jsonPath("$.owner.id").value("00000000-0000-0000-0000-000000000000"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void testFindOne_ShouldReturnErrorMessage() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(get(BASE_URL + "/{id}", accounts.get(0).getId()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Ресурс не найден"))
                .andExpect(jsonPath("$.details").isEmpty())
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void testSave_ShouldReturnOk() {
        when(repository.save(any())).thenReturn(accounts.get(0));
        when(mapper.asDTO(any())).thenReturn(accountDtos.get(0));

        mockMvc.perform(post(BASE_URL)
                        .content(asJson(accountDtos.get(0)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("00000000-0000-0000-0000-00000000000a"))
                .andExpect(jsonPath("$.currency").value("RED"))
                .andExpect(jsonPath("$.amount").value("100.0"))
                .andExpect(jsonPath("$.owner.id").value("00000000-0000-0000-0000-000000000000"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void testSaveById_ShouldReturnNotFound() {

        mockMvc.perform(put(BASE_URL + "/{id}", accounts.get(0).getId())
                        .content(asJson(accountDtos.get(0)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    @SneakyThrows
    void testDoTransaction_ShouldReturnOk() {
        var content = new TransactionRequest(accounts.get(0).getId(), 50d);
        doNothing().when(service).doTransaction(content.accountId(), content.amount());

        mockMvc.perform(put(BASE_URL + "/transaction")
                        .content(asJson(content))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Транзакция успешно завершена"))
                .andDo(print());

        verify(service).doTransaction(content.accountId(), content.amount());
    }

    @Test
    @SneakyThrows
    void testDoTransaction_ShouldReturnBadRequest() {
        var content = new TransactionRequest(accounts.get(0).getId(), -500d);
        doThrow(new DriverException("На счете недостаточно средств")).when(service).doTransaction(content.accountId(), content.amount());

        mockMvc.perform(put(BASE_URL + "/transaction")
                        .content(asJson(content))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("На счете недостаточно средств"))
                .andExpect(jsonPath("$.details").isEmpty())
                .andDo(print());

        verify(service).doTransaction(content.accountId(), content.amount());
    }

    @Test
    @SneakyThrows
    void testDelete_ShouldReturnOk() {
        doNothing().when(service).deleteAccount(any());

        mockMvc.perform(delete(BASE_URL + "/{id}", accounts.get(0).getId()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value("Счет успешно удален"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void testDelete_ShouldReturnBadRequest() {
        doThrow(new DriverException("Невозможно удалить счет с ненулевым балансом"))
                .when(service).deleteAccount(any());

        mockMvc.perform(delete(BASE_URL + "/{id}", accounts.get(0).getId()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Невозможно удалить счет с ненулевым балансом"))
                .andExpect(jsonPath("$.details").isEmpty())
                .andDo(print());
    }
}
