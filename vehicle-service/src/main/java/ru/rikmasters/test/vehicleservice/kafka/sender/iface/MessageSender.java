package ru.rikmasters.test.vehicleservice.kafka.sender.iface;

import ru.rikmasters.test.vehicleservice.kafka.dto.CarKafkaDto;

public interface MessageSender {
    void send(CarKafkaDto dto);
}