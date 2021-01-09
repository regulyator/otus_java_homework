package ru.otus.appcontainer.exceptions;

public class ComponentInitializeException extends RuntimeException {

    public ComponentInitializeException(String message) {
        super(message);
    }

    public ComponentInitializeException(String message, Throwable cause) {
        super(message, cause);
    }
}
