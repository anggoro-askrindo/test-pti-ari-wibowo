package com.example.assesmentbackend.exception;

import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
public class DataNotFoundException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public DataNotFoundException(String message) {
        this.message = message;
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
