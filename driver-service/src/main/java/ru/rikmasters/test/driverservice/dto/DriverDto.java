package ru.rikmasters.test.driverservice.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class DriverDto {
    private UUID id;
    private String fio;
    private Date birthdate;
    private String passport;
    private String licenseCategory;
    private Integer driverExperience;
    private Double accountBalance;
}
