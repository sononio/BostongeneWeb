package com.sononio.bostongene.web.exceptions;

/**
 * Exception for duplicating emails.
 */
public class ApiEmailExistException extends ApiException {
    public ApiEmailExistException(String message) {
        super(ApiExceptionType.EMAIL_EXIST, message);
    }
}
