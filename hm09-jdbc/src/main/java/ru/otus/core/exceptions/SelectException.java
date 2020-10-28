package ru.otus.core.exceptions;

public class SelectException extends RuntimeException {

    public SelectException(String message) {
        super(message);
    }

    public SelectException(String message, Throwable cause) {
        super(message, cause);
    }
}
