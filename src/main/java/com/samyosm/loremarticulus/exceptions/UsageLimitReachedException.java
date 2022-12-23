package com.samyosm.loremarticulus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsageLimitReachedException extends ResponseStatusException {

    private static final String message = "Usage limit reached";
    private static final HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
    public UsageLimitReachedException() {
        super(httpStatus, message);
    }

    public UsageLimitReachedException(String message) {
        super(httpStatus, message);
    }

    public UsageLimitReachedException(String message, Throwable cause) {
        super(httpStatus, message, cause);
    }
}
