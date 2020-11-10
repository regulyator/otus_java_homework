package ru.otus.core.exceptions;

public class FillEntityException extends CoreException {

    public FillEntityException(String message) {
        super(message);
    }

    public FillEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
