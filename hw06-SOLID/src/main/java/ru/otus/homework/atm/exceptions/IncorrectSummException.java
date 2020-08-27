package ru.otus.homework.atm.exceptions;

public class IncorrectSummException extends RuntimeException {

    public IncorrectSummException(String message) {
        super(message);
    }

    public IncorrectSummException(String message, Throwable cause) {
        super(message, cause);
    }
}
