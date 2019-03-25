package com.sononio.bostongene.web.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception types for API.
 */
public enum ApiExceptionType {
    EMAIL_EXIST,
    USER_NOT_FOUND;

    public HttpStatus getHttpStatus() {
        switch (this) {
            case USER_NOT_FOUND:
                return HttpStatus.NOT_FOUND;
            case EMAIL_EXIST:
                return HttpStatus.BAD_REQUEST;
            default:
                return HttpStatus.BAD_REQUEST;
        }
    }
}
