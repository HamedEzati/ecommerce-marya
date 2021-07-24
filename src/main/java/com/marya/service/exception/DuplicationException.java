package com.marya.service.exception;

public class DuplicationException extends RuntimeException {
    private String message;

    public DuplicationException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
