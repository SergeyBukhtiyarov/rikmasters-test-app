package ru.rikmasters.test.vehicleservice.mapper;

import common.mapper.GenericMapper;
import org.mapstruct.Mapper;
import ru.rikmasters.test.vehicleservice.dto.DetailDto;
import ru.rikmasters.test.vehicleservice.entity.Detail;

@Mapper(componentModel = "spring")
public interface DetailMapper extends GenericMapper<Detail, DetailDto> {
}
