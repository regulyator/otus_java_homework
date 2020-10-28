package ru.otus.core.exceptions;

public class FillEntityException extends RuntimeException {

    public FillEntityException(String message) {
        super(message);
    }

    public FillEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
