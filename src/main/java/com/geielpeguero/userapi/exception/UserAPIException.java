package com.geielpeguero.userapi.exception;

import com.geielpeguero.userapi.model.ExceptionMessage;

public class UserAPIException extends RuntimeException {

    public UserAPIException(ExceptionMessage message) {
        super(message.toString());
    }
}
