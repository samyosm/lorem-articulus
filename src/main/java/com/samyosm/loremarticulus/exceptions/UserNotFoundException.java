package com.samyosm.loremarticulus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

    private static final String message = "User not found";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public UserNotFoundException() {
        super(httpStatus, message);
    }

    public UserNotFoundException(String message) {
        super(httpStatus, message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(httpStatus, message, cause);
    }
}
