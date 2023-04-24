package ru.rikmasters.test.vehicleservice.kafka.sender.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.rikmasters.test.vehicleservice.kafka.dto.CarKafkaDto;
import ru.rikmasters.test.vehicleservice.kafka.exception.KafkaCarException;
import ru.rikmasters.test.vehicleservice.kafka.sender.iface.MessageSender;

import static ru.rikmasters.test.vehicleservice.kafka.config.KafkaConfig.TOPIC_ID;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMessageSender implements MessageSender {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void send(CarKafkaDto dto) {
//        log.info("send car:{}", dto);
        String messageAsString;
        try {
            messageAsString = objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException ex) {
//            log.error("can't serialize car:{}", dto, ex);
            throw new KafkaCarException("can't send employee:" + dto, ex);
        }
        kafkaTemplate.send(TOPIC_ID, messageAsString);
    }
}
