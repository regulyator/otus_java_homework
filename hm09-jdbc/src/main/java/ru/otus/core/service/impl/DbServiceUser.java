package ru.otus.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.Dao;
import ru.otus.core.model.User;
import ru.otus.core.service.AbstractDBService;
import ru.otus.core.service.DBService;
import ru.otus.core.service.DbServiceException;

import java.util.Optional;

public class DbServiceUser extends AbstractDBService<User, Long> implements DBService<User, Long> {

    public DbServiceUser(Dao<User, Long> dao) {
        super(dao);
    }
}
