package com.geielpeguero.userapi.model;

public enum ExceptionMessage {

    exist("El correo ya esta registrado", 409),
    tokenExpired("El token ha expirado", 401);

    public final String message;
    public final int httpCode;

    ExceptionMessage(String message, int httpCode) {
        this.message = message;
        this.httpCode = httpCode;
    }
}
