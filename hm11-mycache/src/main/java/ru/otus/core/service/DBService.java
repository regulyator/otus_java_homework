package ru.otus.core.service;

import java.util.Optional;

public interface DBService<T, ID> {

    ID save(T entity);

    Optional<T> get(ID id);
}
