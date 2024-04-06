package org.example.exception;

public class WriteDataException extends RuntimeException {
    public WriteDataException() {
    }

    public WriteDataException(String message) {
        super(message);
    }
}
