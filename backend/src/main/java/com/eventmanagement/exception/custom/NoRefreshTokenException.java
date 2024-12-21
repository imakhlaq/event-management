package com.eventmanagement.exception.custom;

import org.springframework.http.HttpStatus;

public class NoRefreshTokenException extends AbstractCustomException {
    public NoRefreshTokenException(String path, HttpStatus statusCode, String message) {
        super(path, statusCode, message);
    }
}
