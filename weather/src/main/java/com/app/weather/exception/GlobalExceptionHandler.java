package com.app.weather.exception;

import com.app.weather.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessLogicException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO handleMethodValidationFailure(final BusinessLogicException ex) {
        return new ExceptionDTO(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")), ex.getMessage());
    }

}
