package ru.otus.hibernate.exceptions;

import ru.otus.core.exceptions.CoreException;

public class HibernateDaoException extends CoreException {

    public HibernateDaoException(String message) {
        super(message);
    }

    public HibernateDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
