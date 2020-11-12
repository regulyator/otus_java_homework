package ru.otus.webserver.services;

import org.eclipse.jetty.security.AbstractLoginService;
import org.eclipse.jetty.util.security.Password;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;

import java.util.Optional;

public class HibernateLoginService extends AbstractLoginService {

    private final DBServiceUser<User, Long> dbServiceUser;

    public HibernateLoginService(DBServiceUser<User, Long> dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    protected String[] loadRoleInfo(UserPrincipal userPrincipal) {
        return new String[]{Role.ADMIN.name()};
    }

    @Override
    protected UserPrincipal loadUserInfo(String login) {
        System.out.printf("InMemoryLoginService#loadUserInfo(%s)%n", login);
        Optional<User> dbUser = dbServiceUser.getByUsername(login);
        return dbUser.map(u -> new UserPrincipal(u.getUsername(), new Password(u.getPassword()))).orElse(null);
    }
}
