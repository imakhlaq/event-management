package com.eventmanagement.exception.handlers;

import com.eventmanagement.exception.custom.NoRefreshTokenException;
import com.eventmanagement.exception.custom.NoUserFoundException;
import com.eventmanagement.response.error.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Value("${internal-server-error-message}")
    private String internalServerErrorMessage;

    @ExceptionHandler(NoRefreshTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNoRefreshTokenException(NoRefreshTokenException e, HttpServletRequest request) {

        log.error("User has no refresh token");
        var customException = ErrorResponse.builder()
            .message(e.message)
            .statusCode(e.statusCode)
            .timestamp(LocalDateTime.now())
            .path(request.getServletPath())
            .build();
        return ResponseEntity.badRequest().body(customException);
    }

    @ExceptionHandler(NoUserFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNoUserFoundException(NoUserFoundException e, HttpServletRequest request) {

        log.error("User doesn't exists");
        var customException = ErrorResponse.builder()
            .message(e.message)
            .statusCode(e.statusCode)
            .timestamp(LocalDateTime.now())
            .path(request.getServletPath())
            .build();
        return ResponseEntity.badRequest().body(customException);
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleDataAccessException(NoUserFoundException e, HttpServletRequest request) {

        log.error("DataAccessException cause {}", e.getMessage());
        var customException = ErrorResponse.builder()
            .message(e.message)
            .statusCode(e.statusCode)
            .timestamp(LocalDateTime.now())
            .path(request.getServletPath())
            .build();
        return ResponseEntity.badRequest().body(customException);
    }

    @ExceptionHandler(GeneralSecurityException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleGeneralSecurityException(GeneralSecurityException e, HttpServletRequest request) {

        log.error("GeneralSecurityException cause {}", e.getMessage());
        var customException = ErrorResponse.builder()
            .message(internalServerErrorMessage)
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
            .timestamp(LocalDateTime.now())
            .path(request.getServletPath())
            .build();
        return ResponseEntity.badRequest().body(customException);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleIOException(IOException e, HttpServletRequest request) {

        log.error("IOException cause {}", e.getMessage());
        var customException = ErrorResponse.builder()
            .message(internalServerErrorMessage)
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
            .timestamp(LocalDateTime.now())
            .path(request.getServletPath())
            .build();
        return ResponseEntity.badRequest().body(customException);
    }
}
