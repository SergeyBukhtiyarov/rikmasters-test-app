package ru.rikmasters.test.vehicleservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.rikmasters.test.vehicleservice.entity.Car;

import java.util.UUID;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, UUID> {
}
