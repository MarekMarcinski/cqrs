package com.marcinski.complaintquery.api.exceptionhandler;

import com.marcinski.complaintquery.api.dto.ApiError;
import com.marcinski.complaintquery.infrastructure.exception.InvalidSortPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidSortPropertyException.class)
    public ResponseEntity<ApiError> on(InvalidSortPropertyException ex) {
        log.warn(ex.getMessage());
        var apiError = ApiError.of(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> on(Exception ex) {
        log.error(ex.getMessage());
        var apiError = ApiError.of(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List<String> validationMessages = errors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        var apiError = ApiError.of("Wrong body", validationMessages);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
