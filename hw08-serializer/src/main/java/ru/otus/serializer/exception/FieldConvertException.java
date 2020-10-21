package ru.otus.serializer.exception;

public class FieldConvertException extends RuntimeException {

    public FieldConvertException(String message) {
        super(message);
    }

    public FieldConvertException(String message, Throwable cause) {
        super(message, cause);
    }
}
