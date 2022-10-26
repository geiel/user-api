package com.geielpeguero.userapi.controller;

import com.geielpeguero.userapi.exception.UserAPIException;
import com.geielpeguero.userapi.model.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(UserAPIException.class)
    protected ResponseEntity<Map<String, String>> handleUserAPIException(UserAPIException exception) {
        ExceptionMessage exceptionMessage = ExceptionMessage.valueOf(exception.getMessage());
        Map<String, String> message = new HashMap<>();
        message.put("message", exceptionMessage.message);

        return new ResponseEntity<>(message, HttpStatus.valueOf(exceptionMessage.httpCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put("message", fieldName + " " + errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
