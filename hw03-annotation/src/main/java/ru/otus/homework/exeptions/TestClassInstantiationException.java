package ru.otus.homework.exeptions;

public class TestClassInstantiationException extends RuntimeException {

    public TestClassInstantiationException(String message) {
        super(message);
    }

    public TestClassInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
