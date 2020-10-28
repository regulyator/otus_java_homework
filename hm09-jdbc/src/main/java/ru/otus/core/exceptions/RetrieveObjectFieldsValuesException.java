package ru.otus.core.exceptions;

public class RetrieveObjectFieldsValuesException extends RuntimeException {

    public RetrieveObjectFieldsValuesException(String message) {
        super(message);
    }

    public RetrieveObjectFieldsValuesException(String message, Throwable cause) {
        super(message, cause);
    }
}
