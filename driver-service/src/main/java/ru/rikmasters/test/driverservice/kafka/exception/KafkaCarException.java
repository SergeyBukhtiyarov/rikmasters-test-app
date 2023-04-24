package ru.rikmasters.test.driverservice.kafka.exception;

public class KafkaCarException extends RuntimeException {
    public KafkaCarException(String message, Throwable cause) {
        super(message, cause);
    }
}