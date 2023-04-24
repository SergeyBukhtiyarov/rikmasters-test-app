package ru.rikmasters.test.driverservice.controller;

import common.controller.MainAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rikmasters.test.driverservice.dto.DriverDto;
import ru.rikmasters.test.driverservice.entity.Driver;
import ru.rikmasters.test.driverservice.mapper.DriverMapper;
import ru.rikmasters.test.driverservice.repository.DriverRepository;
import ru.rikmasters.test.driverservice.service.DriverService;

@RestController
@RequestMapping("/drivers/driver")
public class DriverController extends MainAbstractController<Driver, DriverDto> {

    private final DriverService service;

    @Autowired
    public DriverController(DriverRepository repository, DriverMapper mapper, DriverService service) {
        super(Driver.class, DriverDto.class, mapper, repository);
        this.service = service;
    }
}
