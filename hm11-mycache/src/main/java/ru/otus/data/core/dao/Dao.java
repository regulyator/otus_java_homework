package ru.otus.data.core.dao;

import ru.otus.data.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface Dao<T, ID> {
    Optional<T> findById(ID id);

    ID insert(T entity);

    void update(T entity);

    void insertOrUpdate(T entity);

    SessionManager getSessionManager();
}
