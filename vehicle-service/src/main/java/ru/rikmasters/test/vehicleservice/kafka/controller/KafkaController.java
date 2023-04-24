package ru.rikmasters.test.vehicleservice.kafka.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rikmasters.test.vehicleservice.kafka.sender.iface.KafkaSender;

@RequestMapping("/vehicle/kafka")
@RestController
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaSender kafkaSender;
    
    @GetMapping("/send_all")
    public ResponseEntity<Void> sendAll(
    ) {
        kafkaSender.sendAll();
        return ResponseEntity.ok().build();
    }
}