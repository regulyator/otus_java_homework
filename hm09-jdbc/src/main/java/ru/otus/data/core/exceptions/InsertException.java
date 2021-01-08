package ru.otus.data.core.exceptions;

public class InsertException extends CoreException {

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }
}
