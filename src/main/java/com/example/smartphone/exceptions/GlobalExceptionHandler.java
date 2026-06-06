package com.example.smartphone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundExceptioon.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundExceptioon ex){
        ErrorResponse error = new ErrorResponse(
          ex.getMessage(), LocalDateTime.now(),HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserError(UserException ex){
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(), LocalDateTime.now(),HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
