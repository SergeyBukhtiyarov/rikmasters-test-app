package ru.rikmasters.test.driverservice.kafka.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.rikmasters.test.driverservice.entity.Car;

import java.util.UUID;

public interface CarRepository extends PagingAndSortingRepository<Car, UUID> {
}
