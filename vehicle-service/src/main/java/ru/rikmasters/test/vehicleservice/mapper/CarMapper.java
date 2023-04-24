package ru.rikmasters.test.vehicleservice.mapper;

import common.mapper.GenericMapper;
import org.mapstruct.Mapper;
import ru.rikmasters.test.vehicleservice.dto.CarDto;
import ru.rikmasters.test.vehicleservice.entity.Car;

@Mapper(componentModel = "spring")
public interface CarMapper extends GenericMapper<Car, CarDto> {
}
