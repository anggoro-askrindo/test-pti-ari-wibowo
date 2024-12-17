package com.example.assesmentbackend.exception;

import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
public class JwtExpiredException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public JwtExpiredException(String message) {
        this.message = message;
        this.httpStatus = HttpStatus.FORBIDDEN;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
