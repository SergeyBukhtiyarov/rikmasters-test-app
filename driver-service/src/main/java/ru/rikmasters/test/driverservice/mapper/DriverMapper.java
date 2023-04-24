package ru.rikmasters.test.driverservice.mapper;

import common.mapper.GenericMapper;
import org.mapstruct.Mapper;
import ru.rikmasters.test.driverservice.dto.DriverDto;
import ru.rikmasters.test.driverservice.entity.Driver;

@Mapper(componentModel = "spring")
public interface DriverMapper extends GenericMapper<Driver, DriverDto> {
    @Override
    Driver asEntity(DriverDto dto);

    @Override
    DriverDto asDTO(Driver entity);
}
