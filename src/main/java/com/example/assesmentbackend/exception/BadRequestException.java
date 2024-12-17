package com.example.assesmentbackend.exception;

import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
public class BadRequestException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public BadRequestException(String message) {
        this.message = message;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
