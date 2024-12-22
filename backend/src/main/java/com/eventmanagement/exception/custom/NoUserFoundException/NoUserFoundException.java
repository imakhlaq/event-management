package com.eventmanagement.exception.custom.NoUserFoundException;

import com.eventmanagement.exception.custom.AbstractCustomException;
import org.springframework.http.HttpStatus;

public class NoUserFoundException extends AbstractCustomException {
    public NoUserFoundException(String path, HttpStatus statusCode, String message) {
        super(path, statusCode, message);
    }
}
