package com.victor.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NameNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNameNotFoundException(
            NameNotFoundException exception
    ) {
        return ResponseEntity.status(
                HttpStatus.BAD_REQUEST
        ).body(
                new ExceptionResponse(
                        exception.getMessage()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(
            Exception exception
    ) {
        return ResponseEntity.status(
                HttpStatus.BAD_REQUEST
        ).body(
                new ExceptionResponse(
                        exception.getMessage()
                )
        );
    }
}
