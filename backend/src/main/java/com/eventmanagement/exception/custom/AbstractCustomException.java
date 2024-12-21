package com.eventmanagement.exception.custom;

import org.springframework.http.HttpStatus;

public abstract class AbstractCustomException extends RuntimeException {
    private String message;
    private String path;
    private HttpStatus statusCode;

    public AbstractCustomException(String path, HttpStatus statusCode, String message) {
        this.path = path;
        this.statusCode = statusCode;
        this.message = message;
    }
}
