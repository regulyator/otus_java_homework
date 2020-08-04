package ru.otus.homework.exeptions;

public class TestMethodInvocationException extends RuntimeException {

    public TestMethodInvocationException(String message) {
        super(message);
    }

    public TestMethodInvocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
