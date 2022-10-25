package com.geielpeguero.userapi.controller;

import com.geielpeguero.userapi.exception.UserAPIException;
import com.geielpeguero.userapi.model.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(UserAPIException.class)
    protected ResponseEntity<Map<String, String>> generateUserAPIException(
            UserAPIException exception, HttpServletRequest httpRequest, WebRequest webRequest) {
        ExceptionMessage exceptionMessage = ExceptionMessage.valueOf(exception.getMessage());
        Map<String, String> message = new HashMap<>();
        message.put("message", exceptionMessage.message);

        return new ResponseEntity<>(message, HttpStatus.valueOf(exceptionMessage.httpCode));
    }
}
