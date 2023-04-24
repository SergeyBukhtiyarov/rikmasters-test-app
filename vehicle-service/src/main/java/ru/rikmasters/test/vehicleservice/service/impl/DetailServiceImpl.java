package ru.rikmasters.test.vehicleservice.service.impl;

import common.exception.VehicleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rikmasters.test.vehicleservice.dto.DetailDto;
import ru.rikmasters.test.vehicleservice.entity.Detail;
import ru.rikmasters.test.vehicleservice.repository.DetailRepository;
import ru.rikmasters.test.vehicleservice.service.DetailService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {

    private final DetailRepository repository;
    @Override
    public List<Detail> findAllByCarId(UUID id) {
        List<Detail> allByCarId = repository.findAllByCarId(id);
        if (allByCarId.isEmpty()) {
            throw new VehicleException("Не найдено ни одной детали для выбранного автомобиля");
        }
        return allByCarId;
    }
}
