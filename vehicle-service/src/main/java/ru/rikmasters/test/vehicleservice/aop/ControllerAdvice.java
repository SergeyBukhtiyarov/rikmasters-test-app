package ru.rikmasters.test.vehicleservice.aop;

import common.exception.DriverException;
import common.exception.ResourceNotFoundException;
import common.exception.VehicleException;
import common.model.ErrorMessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {
    @ExceptionHandler(VehicleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageModel driverException(DriverException e) {
        return ErrorMessageModel.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageModel resourceNotFoundException(ResourceNotFoundException e) {
        return ErrorMessageModel.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageModel otherException(Exception e) {
        log.error(e.getMessage(), e);
        return ErrorMessageModel.builder()
                .message(e.getMessage())
                .details(Arrays.toString(e.getStackTrace()))
                .build();
    }
}
