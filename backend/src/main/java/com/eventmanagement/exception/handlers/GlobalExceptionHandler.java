package com.eventmanagement.exception.handlers;

import com.eventmanagement.exception.custom.NoRefreshTokenException;
import com.eventmanagement.response.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoRefreshTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNoRefreshTokenException(Exception e) {

        var customException = ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .build();
        return ResponseEntity.badRequest().body(customException);
    }
}
