package ru.rikmasters.test.driverservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.rikmasters.test.driverservice.entity.Driver;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface DriverRepository extends PagingAndSortingRepository<Driver, UUID> {
    List<Driver> findByBirthdate(Date date);
}
