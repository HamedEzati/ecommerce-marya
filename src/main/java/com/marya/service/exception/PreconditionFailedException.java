package com.marya.service.exception;

public class PreconditionFailedException extends RuntimeException {
    private String message;

    public PreconditionFailedException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
