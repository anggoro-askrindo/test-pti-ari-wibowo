package com.example.assesmentbackend.exception;

import com.example.assesmentbackend.model.response.ApiResponse;
import com.example.assesmentbackend.model.response.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.assesmentbackend.util.MapperUtil.camelCaseToKebabCase;

@Slf4j
@ControllerAdvice(value = "com.example.assesmentbackend.controller")
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<Object> internalErrorException(InternalErrorException ex) {
        log.error("error", ex);
        return ApiResponse.internalServerError(ex.getMessage()).toResponseEntity();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestException(BadRequestException ex) {
        log.error("error", ex);
        return ApiResponse.badRequest(ex.getMessage()).toResponseEntity();
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> dataNotFoundException(DataNotFoundException ex) {
        log.error("error", ex);
        return ApiResponse.notFound(ex.getMessage()).toResponseEntity();
    }

    @ExceptionHandler(JwtExpiredException.class)
    public ResponseEntity<Object> jwtExpiredException(JwtExpiredException ex) {
        log.error("error", ex);
        return ApiResponse.forbidden(ex.getMessage()).toResponseEntity();
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ApiResponse.badRequest(ex.getMessage()).toResponseEntity();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("MethodArgumentNotValidException", ex);
        List<ValidationError> fieldError = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> new ValidationError(camelCaseToKebabCase(x.getField()), x.getDefaultMessage()))
                .collect(Collectors.toList());
        return ApiResponse.validationError("Validation error", fieldError).toResponseEntity();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("HandleHttpMessageNotReadable", ex);
        return ApiResponse.badRequest(ex.getMessage()).toResponseEntity();
    }
}
