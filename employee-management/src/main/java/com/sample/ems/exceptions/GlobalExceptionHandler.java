package com.sample.ems.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.sample.ems.constants.Constants.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ErrorResponse handleHandlerMethodValidationException(HandlerMethodValidationException e){
        Map<String, String> errors = new HashMap<>();

        e.getBeanResults().forEach(error -> {
            error.getFieldErrors().forEach(fieldError -> {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
        });

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .description(VALIDATION_ERROR_DESCRIPTION)
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
        log.error(e.getMessage());
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeAlreadyExistsWirthEmail.class)
    public ErrorResponse handleEmployeeAlreadyExistsWirthEmail(EmployeeAlreadyExistsWirthEmail e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .description(EMPLOYEE_COMPANY_EMAIL_UNIQUE_VALIDATION_MESSAGE)
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        log.error(e.getMessage());
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ErrorResponse handleEmployeeNotFoundException(EmployeeNotFoundException e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .description(EMPLOYEE_NOT_FOUND_ERROR_DESCRIPTION_MESSAGE)
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        log.error(e.getMessage());
        return errorResponse;
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .description(INTERNAL_SERVER_EXCEPTION_ERROR_DESCRIPTION)
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        log.error(e.getMessage());
        return errorResponse;
    }

}
