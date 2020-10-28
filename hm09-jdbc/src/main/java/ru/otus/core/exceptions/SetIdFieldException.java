package ru.otus.core.exceptions;

public class SetIdFieldException extends RuntimeException {

    public SetIdFieldException(String message) {
        super(message);
    }

    public SetIdFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
