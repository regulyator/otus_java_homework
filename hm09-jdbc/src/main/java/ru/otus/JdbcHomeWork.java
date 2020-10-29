package ru.otus;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.AccountDao;
import ru.otus.core.dao.UserDao;
import ru.otus.core.dao.impl.AccountDaoImpl;
import ru.otus.core.dao.impl.UserDaoImpl;
import ru.otus.core.factorys.JdbcMapperFactory;
import ru.otus.core.model.Account;
import ru.otus.core.model.User;
import ru.otus.core.service.impl.DbServiceAccount;
import ru.otus.core.service.impl.DbServiceUser;
import ru.otus.h2.DataSourceH2;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.mapper.JdbcMapper;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.util.Optional;


public class JdbcHomeWork {
    private static final Logger logger = LoggerFactory.getLogger(JdbcHomeWork.class);

    public static void main(String[] args) {
        // Общая часть
        var dataSource = new DataSourceH2();
        flywayMigrations(dataSource);
        var sessionManager = new SessionManagerJdbc(dataSource);
        var jdbcMapperFactory = new JdbcMapperFactory(sessionManager);

        // Работа с пользователем
        DbExecutorImpl<User> dbExecutorUser = new DbExecutorImpl<>();
        JdbcMapper<User> jdbcMapperUser = jdbcMapperFactory.createJdbcMapper(User.class, dbExecutorUser);
        UserDao userDao = new UserDaoImpl(jdbcMapperUser);

        var dbServiceUser = new DbServiceUser(userDao);
        var idUser = dbServiceUser.save(new User(1, "dbServiceUser", 22));
        Optional<User> user = dbServiceUser.get(idUser);

        user.ifPresentOrElse(
                crUser -> logger.info("created user, name:{}", crUser.getName()),
                () -> logger.info("user was not created")
        );
        // Работа со счетом
        DbExecutorImpl<Account> dbExecutorAccount = new DbExecutorImpl<>();
        JdbcMapper<Account> jdbcMapperAccount = jdbcMapperFactory.createJdbcMapper(Account.class, dbExecutorAccount);
        AccountDao accountDao = new AccountDaoImpl(jdbcMapperAccount);

        var dbServiceAccount = new DbServiceAccount(accountDao);
        var id = dbServiceAccount.save(new Account(0, "dbServiceAccount", 2));
        Optional<Account> account = dbServiceAccount.get(id);

        account.ifPresentOrElse(
                crAccount -> logger.info("created account, type:{}", crAccount.getType()),
                () -> logger.info("account was not created")
        );


    }

    private static void flywayMigrations(DataSource dataSource) {
        logger.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        logger.info("db migration finished.");
        logger.info("***");
    }
}
