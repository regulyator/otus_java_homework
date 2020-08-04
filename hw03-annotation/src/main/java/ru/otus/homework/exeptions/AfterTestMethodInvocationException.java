package ru.otus.homework.exeptions;

public class AfterTestMethodInvocationException extends RuntimeException {

    public AfterTestMethodInvocationException(String message) {
        super(message);
    }

    public AfterTestMethodInvocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
