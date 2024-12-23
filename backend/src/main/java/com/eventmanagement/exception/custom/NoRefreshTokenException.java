package com.eventmanagement.exception.custom;

import org.springframework.http.HttpStatus;

public class NoRefreshTokenException extends AbstractCustomException {
    public NoRefreshTokenException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }
}
