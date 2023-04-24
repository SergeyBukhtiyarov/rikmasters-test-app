package ru.rikmasters.test.vehicleservice.kafka.dto;

import lombok.Data;
import ru.rikmasters.test.vehicleservice.dto.CarDto;
import ru.rikmasters.test.vehicleservice.entity.Car;

@Data
public class CarKafkaDto {
    public CarKafkaDto(Car car) {
        this.vin = car.getVin();
        this.plateNumber = car.getPlateNumber();
    }
    public CarKafkaDto(CarDto car) {
        this.vin = car.getVin();
        this.plateNumber = car.getPlateNumber();
    }

    private String vin;
    private String plateNumber;
}
