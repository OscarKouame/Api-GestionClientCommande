package com.testorangeci.gestcom.Exceptions;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiError {
    private int status;
    private String error;
    private String message;
    private LocalDateTime timestamp;
    private List<String> details;

    public ApiError(int status, String message, List<String> details) {
        this.status = status;
        this.error = switch (status) {
            case 400 -> "Bad Request";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            default -> "Error";
        };
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

}
