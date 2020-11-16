package ru.otus.data.core.service;

import ru.otus.data.core.model.User;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

    Long save(User entity);

    Optional<User> get(Long id);

    Optional<User> getByUsername(String username);

    List<User> getAllUsers();
}
