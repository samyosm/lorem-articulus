package com.samyosm.loremarticulus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserTokenException extends ResponseStatusException {

    private static final String message = "Invalid user token";
    private static final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    public InvalidUserTokenException() {
        super(httpStatus, message);
    }

    public InvalidUserTokenException(String message) {
        super(httpStatus, message);
    }

    public InvalidUserTokenException(String message, Throwable cause) {
        super(httpStatus, message, cause);
    }
}
