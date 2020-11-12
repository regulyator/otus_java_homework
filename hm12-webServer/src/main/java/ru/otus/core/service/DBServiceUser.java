package ru.otus.core.service;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser<T, ID> extends DBService<T, ID>{

    Optional<T> getByUsername(String username);

    List<T> getAllUsers();
}
