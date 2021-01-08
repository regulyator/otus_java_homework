package ru.otus.data.core.dao;

import ru.otus.data.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface Dao<T, ID> {
    Optional<T> findById(ID id);

    long insertUser(T entity);

    void updateUser(T entity);

    void insertOrUpdate(T entity);

    SessionManager getSessionManager();
}
