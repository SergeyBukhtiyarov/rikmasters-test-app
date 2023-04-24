package ru.rikmasters.test.driverservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rikmasters.test.driverservice.entity.Driver;
import ru.rikmasters.test.driverservice.repository.DriverRepository;
import ru.rikmasters.test.driverservice.service.DriverService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository repository;

    public List<Driver> findAllByBirthDate(Date today) {
        return repository.findByBirthdate(today);
    }
}
