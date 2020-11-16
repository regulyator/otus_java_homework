package ru.otus.data.core.service;

import java.util.Optional;

public interface DBService<T, ID> {

    long save(T entity);

    Optional<T> get(ID id);
}
