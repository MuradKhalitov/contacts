package org.example.exception;

public class InvalidChoiceCodeException extends RuntimeException {
    public InvalidChoiceCodeException() {
    }

    public InvalidChoiceCodeException(String message) {
        super(message);
    }
}
