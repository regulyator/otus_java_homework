package ru.otus.core.exceptions;

public class GetEntityClassMetaDataException extends RuntimeException {

    public GetEntityClassMetaDataException(String message) {
        super(message);
    }

    public GetEntityClassMetaDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
