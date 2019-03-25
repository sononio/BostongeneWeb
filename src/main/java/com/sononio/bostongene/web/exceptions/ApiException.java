package com.sononio.bostongene.web.exceptions;

/**
 * Base API exception.
 */
public class ApiException extends Exception {
    private ApiExceptionType type;

    public ApiException(ApiExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public ApiExceptionType getType() {
        return type;
    }
}

