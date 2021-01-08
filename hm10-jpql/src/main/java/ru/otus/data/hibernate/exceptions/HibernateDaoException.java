package ru.otus.data.hibernate.exceptions;

import ru.otus.data.core.exceptions.CoreException;

public class HibernateDaoException extends CoreException {

    public HibernateDaoException(String message) {
        super(message);
    }

    public HibernateDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
