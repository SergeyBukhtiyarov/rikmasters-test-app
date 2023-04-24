package ru.rikmasters.test.driverservice.kafka.service.impl.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.rikmasters.test.driverservice.kafka.exception.KafkaCarException;
import ru.rikmasters.test.driverservice.kafka.service.iface.CarSaveService;
import ru.rikmasters.test.driverservice.kafka.service.iface.kafka.KafkaListenerService;

import static ru.rikmasters.test.driverservice.kafka.config.KafkaConfig.TOPIC_ID;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaListenerServiceImpl implements KafkaListenerService {
    
    public static final String GROUP_ID = "testAppGroup";
    private final CarSaveService carSaveService;
    private final ObjectMapper objectMapper;
    
    @KafkaListener(groupId = GROUP_ID, topics = TOPIC_ID)
    public void carListener(String carJson) {
        JSONObject car;
        try {
            car = objectMapper.readValue(carJson, JSONObject.class);
        } catch (Exception ex) {
            log.error("can't parse car:{}", carJson, ex);
            throw new KafkaCarException("can't parse car:" + carJson, ex);
        }
        carSaveService.saveOrUpdate(car);
    }
}
