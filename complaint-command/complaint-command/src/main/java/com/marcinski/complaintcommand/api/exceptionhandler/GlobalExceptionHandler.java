package com.marcinski.complaintcommand.api.exceptionhandler;

import com.marcinski.complaintcommand.api.dto.ApiError;
import com.marcinski.complaintcommand.exceptions.AggregateNotFoundException;
import com.marcinski.complaintcommand.exceptions.ConcurrencyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestNotUsableException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AggregateNotFoundException.class)
    public ResponseEntity<ApiError> on(AggregateNotFoundException ex) {
        log.warn(ex.getMessage());
        var apiError = ApiError.of(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConcurrencyException.class)
    public ResponseEntity<ApiError> on(ConcurrencyException ex) {
        log.error(ex.getMessage());
        var apiError = ApiError.of(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.I_AM_A_TEAPOT);
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
