package ru.otus.data.core.exceptions;

public class SetIdFieldException extends CoreException {

    public SetIdFieldException(String message) {
        super(message);
    }

    public SetIdFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
