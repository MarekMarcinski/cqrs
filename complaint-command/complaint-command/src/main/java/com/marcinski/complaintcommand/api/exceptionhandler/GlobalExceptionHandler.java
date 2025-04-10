package com.marcinski.complaintcommand.api.exceptionhandler;

import com.marcinski.complaintcommand.api.dto.ApiError;
import com.marcinski.complaintcommand.exceptions.AggregateNotFoundException;
import com.marcinski.complaintcommand.exceptions.ConcurrencyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AggregateNotFoundException.class)
    public ResponseEntity<ApiError> on(AggregateNotFoundException ex) {
        log.warn(ex.getMessage());
        var apiError = new ApiError(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConcurrencyException.class)
    public ResponseEntity<ApiError> on(ConcurrencyException ex) {
        log.error(ex.getMessage());
        var apiError = new ApiError(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> on(Exception ex) {
        log.error(ex.getMessage());
        var apiError = new ApiError(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
