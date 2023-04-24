package ru.rikmasters.test.vehicleservice.controller;

import common.controller.MainAbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rikmasters.test.vehicleservice.dto.CarDto;
import ru.rikmasters.test.vehicleservice.entity.Car;
import ru.rikmasters.test.vehicleservice.mapper.CarMapper;
import ru.rikmasters.test.vehicleservice.repository.CarRepository;
import ru.rikmasters.test.vehicleservice.service.CarService;

@RestController
@RequestMapping("/vehicle/car")
public class CarController extends MainAbstractController<Car, CarDto> {
    private final CarService service;

    public CarController(CarMapper mapper, CarRepository repository, CarService service) {
        super(Car.class, CarDto.class, mapper, repository);
        this.service = service;
    }
}
