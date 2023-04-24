package ru.rikmasters.test.vehicleservice.dto;

import lombok.Data;

@Data
public class DetailDto {
    private String serialNumber;
    private String type;
    private CarDto car;
}
