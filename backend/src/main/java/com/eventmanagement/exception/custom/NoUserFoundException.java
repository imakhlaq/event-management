package com.eventmanagement.exception.custom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class NoUserFoundException extends AbstractCustomException {
    public NoUserFoundException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }
}
