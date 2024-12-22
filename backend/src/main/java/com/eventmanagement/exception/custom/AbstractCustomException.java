package com.eventmanagement.exception.custom;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public abstract class AbstractCustomException extends RuntimeException {
    final private String message;
    final private String path;
    final private HttpStatus statusCode;

    public AbstractCustomException(String path, HttpStatus statusCode, String message) {
        super(message);
        this.path = path;
        this.statusCode = statusCode;
        this.message = message;
    }
}
