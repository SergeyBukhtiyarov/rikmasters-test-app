package ru.rikmasters.test.driverservice.service;

import ru.rikmasters.test.driverservice.entity.Driver;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface DriverService {
    List<Driver> findAllByBirthDate(Date today);
}
