package ru.otus.data.core.dao;

import java.util.List;
import java.util.Optional;

public interface UserDao<T, ID> extends Dao<T, ID> {

    Optional<T> findByUsername(String username);

    List<T> getAllUsers();
}
