package ru.otus.core.dao.impl;

import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.mapper.JdbcMapper;

import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final JdbcMapper<User> userJdbcMapper;

    public UserDaoImpl(JdbcMapper<User> userJdbcMapper) {
        this.userJdbcMapper = userJdbcMapper;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userJdbcMapper.findById(id, User.class));
    }

    @Override
    public long insertUser(User user) {
        userJdbcMapper.insert(user);
        return user.getId();
    }

    @Override
    public void updateUser(User user) {
        userJdbcMapper.update(user);
    }

    @Override
    public void insertOrUpdate(User user) {
        userJdbcMapper.insertOrUpdate(user);
    }

    @Override
    public SessionManager getSessionManager() {
        return userJdbcMapper.getSessionManager();
    }
}
