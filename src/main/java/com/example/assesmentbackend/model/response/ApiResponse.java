package com.example.assesmentbackend.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.example.assesmentbackend.util.DateHelperUtil.currentDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("message")
    private String message;
    @JsonProperty("errors")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> validationErrors;
    @JsonIgnore
    private Integer httpStatus;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static <T> ApiResponse<T> ok(T data, String message) {
        return ApiResponse.<T>builder()
                .timestamp(currentDateTime())
                .success(true)
                .message(message)
                .data(data)
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    public static <T> ApiResponse<T> validationError(String message, List<T> validationErrors) {
        return ApiResponse.<T>builder()
                .timestamp(currentDateTime())
                .success(false)
                .message(message)
                .validationErrors(validationErrors)
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    public static <T> ApiResponse<T> badRequest(String message) {
        return ApiResponse.<T>builder()
                .timestamp(currentDateTime())
                .success(false)
                .message(message)
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    public static <T> ApiResponse<T> notFound(String message) {
        return ApiResponse.<T>builder()
                .timestamp(currentDateTime())
                .success(false)
                .message(message)
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .build();
    }

    public static <T> ApiResponse<T> internalServerError(String message) {
        return ApiResponse.<T>builder()
                .timestamp(currentDateTime())
                .success(false)
                .message(message)
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .build();
    }

    public static <T> ApiResponse<T> forbidden(String message) {
        return ApiResponse.<T>builder()
                .timestamp(currentDateTime())
                .success(false)
                .message(message)
                .httpStatus(HttpStatus.FORBIDDEN.value())
                .build();
    }

    @JsonIgnore
    public ResponseEntity<Object> toResponseEntity() {
        return ResponseEntity.status(this.httpStatus).body(this);
    }
}
