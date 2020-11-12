package ru.otus.core.dao;

import java.util.Optional;

public interface UserDao<T, ID> extends Dao<T, ID> {

    Optional<T> findByUsername(String username);
}
