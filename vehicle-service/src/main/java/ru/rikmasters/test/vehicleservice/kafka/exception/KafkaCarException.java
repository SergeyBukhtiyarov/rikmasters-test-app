package ru.rikmasters.test.vehicleservice.kafka.exception;

public class KafkaCarException extends RuntimeException {
    public KafkaCarException(String message, Throwable cause) {
        super(message, cause);
    }
}