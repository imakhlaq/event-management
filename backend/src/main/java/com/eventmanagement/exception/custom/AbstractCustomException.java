package com.eventmanagement.exception.custom;

import org.springframework.http.HttpStatus;

public abstract class AbstractCustomException extends RuntimeException {
    final public String message;
    final public HttpStatus statusCode;

    public AbstractCustomException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }
}
