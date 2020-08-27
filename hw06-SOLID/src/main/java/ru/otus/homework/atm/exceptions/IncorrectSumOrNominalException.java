package ru.otus.homework.atm.exceptions;

public class IncorrectSumOrNominalException extends RuntimeException {

    public IncorrectSumOrNominalException(String message) {
        super(message);
    }

    public IncorrectSumOrNominalException(String message, Throwable cause) {
        super(message, cause);
    }
}
