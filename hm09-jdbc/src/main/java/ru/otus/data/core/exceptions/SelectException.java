package ru.otus.data.core.exceptions;

public class SelectException extends CoreException {

    public SelectException(String message) {
        super(message);
    }

    public SelectException(String message, Throwable cause) {
        super(message, cause);
    }
}
