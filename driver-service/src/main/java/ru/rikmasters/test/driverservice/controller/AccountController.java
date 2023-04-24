package ru.rikmasters.test.driverservice.controller;

import common.controller.MainAbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rikmasters.test.driverservice.dto.AccountDto;
import ru.rikmasters.test.driverservice.entity.Account;
import ru.rikmasters.test.driverservice.enums.Currency;
import ru.rikmasters.test.driverservice.mapper.AccountMapper;
import ru.rikmasters.test.driverservice.records.TransactionRequest;
import ru.rikmasters.test.driverservice.repository.AccountRepository;
import ru.rikmasters.test.driverservice.service.AccountService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/drivers/account")
public class AccountController extends MainAbstractController<Account, AccountDto> {
    private final AccountService service;
    private final AccountMapper mapper;

    public AccountController(AccountRepository repository,
                             AccountMapper mapper,
                             AccountService service) {
        super(Account.class, AccountDto.class, mapper, repository);
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("driver-accounts")
    @Operation(summary = "Получение счетов водителя по ID водителя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ОК", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Не найдено счетов", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content)
    })
    public ResponseEntity<List<AccountDto>> getAccountsByOwner(@RequestParam(name = "ownerId", required = true) UUID ownerId,
                                                               @RequestParam(name = "currency", required = false) Currency currency) {
        var entities = service.findAllAccountsByOwner(ownerId, currency);
        return ResponseEntity.ok(entities.stream().map(mapper::asDTO).toList());
    }

    @Override
    public ResponseEntity<String> saveById(@RequestBody AccountDto accountDto, @PathVariable UUID uuid) {
        return ResponseEntity.notFound().build();
    }

    @PutMapping("transaction")
    @Operation(summary = "Начисление/снятие средств со счета водителя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакция успешно завершена", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Не найдено запрашиваемого счета", content = @Content),
            @ApiResponse(responseCode = "400", description = "Недостаточно средств на счете для снятия", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content)
    })
    public ResponseEntity<String> doTransaction(@RequestBody TransactionRequest request) {
        service.doTransaction(request.accountId(), request.amount());
        return ResponseEntity.ok("Транзакция успешно завершена");
    }

    @Override
    @Operation(summary = "Удаление счета по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Счет успешно удален", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Не найдено запрашиваемого счета", content = @Content),
            @ApiResponse(responseCode = "400", description = "Невозможно удалить счет с ненулевым балансом", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content)
    })
    public ResponseEntity<String> delete(@PathVariable UUID uuid) {
        service.deleteAccount(uuid);
        return ResponseEntity.status(HttpStatus.CREATED).body("Счет успешно удален");
    }

}
