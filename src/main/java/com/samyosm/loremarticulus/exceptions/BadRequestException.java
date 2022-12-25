package com.samyosm.loremarticulus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {

    private static final String message = "Bad request";
    private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    public BadRequestException() {
        super(httpStatus, message);
    }

    public BadRequestException(String message) {
        super(httpStatus, message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(httpStatus, message, cause);
    }
}
