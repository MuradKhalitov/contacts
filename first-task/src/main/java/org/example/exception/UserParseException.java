package org.example.exception;

public class UserParseException extends RuntimeException {
    public UserParseException() {
    }

    public UserParseException(String message) {
        super(message);
    }
}
