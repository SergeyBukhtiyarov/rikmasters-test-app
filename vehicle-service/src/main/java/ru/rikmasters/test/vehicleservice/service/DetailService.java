package ru.rikmasters.test.vehicleservice.service;

import ru.rikmasters.test.vehicleservice.dto.DetailDto;
import ru.rikmasters.test.vehicleservice.entity.Detail;

import java.util.List;
import java.util.UUID;

public interface DetailService {
    List<Detail> findAllByCarId(UUID id);
}
