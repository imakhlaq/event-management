package com.eventmanagement.exception.handlers;

import com.eventmanagement.exception.custom.NoRefreshTokenException;
import com.eventmanagement.response.error.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.GeneralSecurityException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoRefreshTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNoRefreshTokenException(NoRefreshTokenException e, HttpServletRequest request) {

        var customException = ErrorResponse.builder()
            .message(e.message)
            .statusCode(e.statusCode)
            .timestamp(LocalDateTime.now())
            .path(request.getContextPath())
            .build();
        return ResponseEntity.badRequest().body(customException);
    }

    @ExceptionHandler(GeneralSecurityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleGeneralSecurityException(GeneralSecurityException e, HttpServletRequest request) {

        var customException = ErrorResponse.builder()
            .message(e.getMessage())
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
            .timestamp(LocalDateTime.now())
            .path(request.getContextPath())
            .build();
        return ResponseEntity.badRequest().body(customException);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e, HttpServletRequest request) {

        var customException = ErrorResponse.builder()
            .message(e.getMessage())
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
            .timestamp(LocalDateTime.now())
            .path(request.getContextPath())
            .build();
        return ResponseEntity.badRequest().body(customException);
    }
}
