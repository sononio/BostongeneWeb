package com.sononio.bostongene.web.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for API exceptions for HTTP responses.
 */
@Data
@NoArgsConstructor
public class ApiErrorContainer {
    private Map<String, String> apiError;

    public ApiErrorContainer(ApiExceptionType type, String message) {
        apiError = new HashMap<>();
        apiError.put("errorType", String.valueOf(type));
        apiError.put("message", message);
    }

    public Map<String, String> getApiError() {
        return apiError;
    }
}
