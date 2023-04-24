package ru.rikmasters.test.vehicleservice.kafka.sender.impl;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rikmasters.test.vehicleservice.dto.CarDto;
import ru.rikmasters.test.vehicleservice.entity.Car;
import ru.rikmasters.test.vehicleservice.kafka.dto.CarKafkaDto;
import ru.rikmasters.test.vehicleservice.kafka.sender.iface.KafkaSender;
import ru.rikmasters.test.vehicleservice.repository.CarRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class KafkaSenderImpl implements KafkaSender {
    
    private final CarRepository carRepository;
    private final KafkaMessageSender kafkaMessageSender;
    
    @Override
    public void sendAll() {
        List<Car> employeeKafkaDtos = Lists.newArrayList(carRepository.findAll());
        employeeKafkaDtos.forEach(this::send);
    }
    
    @Override
    public void send(Car car) {
        kafkaMessageSender.send(new CarKafkaDto(car));
    }
    
    @Override
    public void send(CarDto carDto) {
        kafkaMessageSender.send(new CarKafkaDto(carDto));
    }
}
