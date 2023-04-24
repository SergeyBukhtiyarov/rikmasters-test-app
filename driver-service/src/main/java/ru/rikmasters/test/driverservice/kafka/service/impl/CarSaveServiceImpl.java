package ru.rikmasters.test.driverservice.kafka.service.impl;


import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rikmasters.test.driverservice.dto.CarDto;
import ru.rikmasters.test.driverservice.kafka.mapper.CarMapper;
import ru.rikmasters.test.driverservice.kafka.repository.CarRepository;
import ru.rikmasters.test.driverservice.kafka.service.iface.CarSaveService;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CarSaveServiceImpl implements CarSaveService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public void saveOrUpdate(JSONObject carJson) {
        UUID carId = getEmployeeId(carJson);
        carRepository.save(carMapper.asEntity(new CarDto(carId, carJson)));
    }

    public UUID getEmployeeId(JSONObject carJson) {
        String id = (String) (carJson.get("id"));
        return UUID.fromString(id);
    }
}
