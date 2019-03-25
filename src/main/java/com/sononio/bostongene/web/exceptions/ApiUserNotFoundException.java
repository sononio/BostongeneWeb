package com.sononio.bostongene.web.exceptions;

/**
 * Exception for searching for nonexistent user.
 */
public class ApiUserNotFoundException extends ApiException {
    public ApiUserNotFoundException(String message) {
        super(ApiExceptionType.USER_NOT_FOUND, message);
    }
}

