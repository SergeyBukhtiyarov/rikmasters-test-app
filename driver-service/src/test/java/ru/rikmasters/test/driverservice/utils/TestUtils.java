package ru.rikmasters.test.driverservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.rikmasters.test.driverservice.dto.AccountDto;
import ru.rikmasters.test.driverservice.dto.DriverDto;
import ru.rikmasters.test.driverservice.entity.Account;
import ru.rikmasters.test.driverservice.entity.Driver;
import ru.rikmasters.test.driverservice.enums.Currency;
import ru.rikmasters.test.driverservice.records.TransactionRequest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class TestUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String asJson(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o);
    }

    public static Driver makeDriver() {
        var driver = new Driver();
        driver.setId(getId(0));
        driver.setFio("Иванов Иван Водителевич");
        driver.setPassport("3311000123");
        driver.setBirthdate(getDate());
        driver.setDriverExperience(10);
        driver.setLicenseCategory("B1C1D1");
        return driver;
    }

    public static DriverDto makeDriverDto() {
        var driver = new DriverDto();
        driver.setId(getId(0));
        driver.setFio("Иванов Иван Водителевич");
        driver.setPassport("3311000123");
        driver.setBirthdate(getDate());
        driver.setDriverExperience(10);
        driver.setLicenseCategory("B1C1D1");
        return driver;
    }

    public static List<Account> makeAccounts() {
        var redAcc = new Account();
        var greenAcc = new Account();
        var blueAcc = new Account();
        redAcc.setId(getId(10));
        redAcc.setCurrency(Currency.RED);
        redAcc.setBalance(100d);
        redAcc.setOwner(makeDriver());

        greenAcc.setId(getId(11));
        greenAcc.setCurrency(Currency.GREEN);
        greenAcc.setBalance(200d);
        greenAcc.setOwner(makeDriver());

        blueAcc.setId(getId(12));
        blueAcc.setCurrency(Currency.BLUE);
        blueAcc.setBalance(0d);
        blueAcc.setOwner(makeDriver());
        return List.of(redAcc, greenAcc, blueAcc);
    }

    public static List<AccountDto> makeAccountsDto() {
        var redAcc = new AccountDto();
        var greenAcc = new AccountDto();
        var blueAcc = new AccountDto();
        redAcc.setId(getId(10));
        redAcc.setCurrency(Currency.RED);
        redAcc.setAmount(100d);
        redAcc.setOwner(makeDriverDto());

        greenAcc.setId(getId(11));
        greenAcc.setCurrency(Currency.GREEN);
        greenAcc.setAmount(200d);
        greenAcc.setOwner(makeDriverDto());

        blueAcc.setId(getId(12));
        blueAcc.setCurrency(Currency.BLUE);
        blueAcc.setAmount(0d);
        blueAcc.setOwner(makeDriverDto());
        return List.of(redAcc, greenAcc, blueAcc);
    }

    public static UUID getId(long i) {
        return new UUID(0L, i);
    }

    public static Date getDate() {
        return Date.valueOf(LocalDate.of(2023,4,22));
    }
}
