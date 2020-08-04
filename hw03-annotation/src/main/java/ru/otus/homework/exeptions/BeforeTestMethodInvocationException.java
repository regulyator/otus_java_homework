package ru.otus.homework.exeptions;

public class BeforeTestMethodInvocationException extends RuntimeException {

    public BeforeTestMethodInvocationException(String message) {
        super(message);
    }

    public BeforeTestMethodInvocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
