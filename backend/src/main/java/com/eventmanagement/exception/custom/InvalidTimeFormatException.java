package com.eventmanagement.exception.custom;

import org.springframework.http.HttpStatus;

public class InvalidTimeFormatException extends AbstractCustomException {

    public InvalidTimeFormatException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }
}
