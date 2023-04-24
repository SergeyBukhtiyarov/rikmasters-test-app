package ru.rikmasters.test.vehicleservice.dto;

import lombok.Data;
import ru.rikmasters.test.vehicleservice.entity.Detail;

import java.util.List;

@Data
public class CarDto {
    private String vin;
    private String plateNumber;
    private List<Detail> details;
}
