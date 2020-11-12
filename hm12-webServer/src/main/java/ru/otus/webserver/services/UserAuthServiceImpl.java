package ru.otus.webserver.services;


import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;

public class UserAuthServiceImpl implements UserAuthService {

    private final DBServiceUser<User, Long> dbServiceUser;

    public UserAuthServiceImpl(DBServiceUser<User, Long> dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return dbServiceUser.getByUsername(login)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

}
