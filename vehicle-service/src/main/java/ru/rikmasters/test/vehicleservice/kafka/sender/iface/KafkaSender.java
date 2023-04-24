package ru.rikmasters.test.vehicleservice.kafka.sender.iface;

import ru.rikmasters.test.vehicleservice.dto.CarDto;
import ru.rikmasters.test.vehicleservice.entity.Car;

public interface KafkaSender {
    
    void sendAll();
    void send(Car car);
    void send(CarDto employeeDto);
}
