package ru.rikmasters.test.driverservice.kafka.mapper;

import common.mapper.GenericMapper;
import org.mapstruct.Mapper;
import ru.rikmasters.test.driverservice.dto.CarDto;
import ru.rikmasters.test.driverservice.entity.Car;

@Mapper(componentModel = "spring")
public interface CarMapper extends GenericMapper<Car, CarDto> {
}
