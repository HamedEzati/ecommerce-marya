package com.marya.service.exception;

public class ValidationException extends RuntimeException {
    private String message;

    public ValidationException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
