package com.eventmanagement.exception.custom.NoUserFoundException;

import com.eventmanagement.exception.custom.AbstractCustomException;
import org.springframework.http.HttpStatus;

public class NoUserFoundException extends AbstractCustomException {
    public NoUserFoundException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }
}
