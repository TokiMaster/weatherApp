package com.app.weather.dto;

import java.io.Serializable;

public class ExceptionDTO implements Serializable {

    private final String exceptionTime;

    private final String message;

    public ExceptionDTO(String exceptionTime, String message) {
        this.exceptionTime = exceptionTime;
        this.message = message;
    }

    public String getExceptionTime() {
        return exceptionTime;
    }

    public String getMessage() {
        return message;
    }

}
