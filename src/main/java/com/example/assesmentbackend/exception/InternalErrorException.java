package com.example.assesmentbackend.exception;

import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
public class InternalErrorException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public InternalErrorException(String message) {
        this.message = message;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
