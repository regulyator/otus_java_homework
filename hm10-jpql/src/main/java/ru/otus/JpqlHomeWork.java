package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.AddressDataSet;
import ru.otus.core.model.PhoneDataSet;
import ru.otus.core.model.User;
import ru.otus.core.service.impl.DbServiceUser;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class JpqlHomeWork {
    public static final String HIBERNATE_CONFIG = "hibernate.cfg.xml";
    private static final Logger logger = LoggerFactory.getLogger(JpqlHomeWork.class);

    public static void main(String[] args) {
        // Общая часть
        var sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CONFIG, User.class, AddressDataSet.class, PhoneDataSet.class);

        // Работа с пользователем
        try (var sessionManager = new SessionManagerHibernate(sessionFactory)) {
            UserDao userDao = new UserDaoHibernate(sessionManager);
            var dbServiceUser = new DbServiceUser(userDao);

            var address = new AddressDataSet("sample street");
            ArrayList<PhoneDataSet> phones = new ArrayList<>(List.of(
                    new PhoneDataSet("123456789"),
                    new PhoneDataSet("987654321")
            ));
            var user = new User("sample user", address, phones);

            var newUserId = dbServiceUser.save(user);
            Optional<User> inBaseUser = dbServiceUser.get(newUserId);
            inBaseUser.ifPresentOrElse(
                    crUser -> logger.info("created user, name:{}", crUser.toString()),
                    () -> logger.info("user was not created"));
        }
    }
}
