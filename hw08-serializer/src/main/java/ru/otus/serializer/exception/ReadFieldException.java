package ru.otus.serializer.exception;

public class ReadFieldException extends RuntimeException {

    public ReadFieldException(String message) {
        super(message);
    }

    public ReadFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
