package ru.otus.core.service.impl;

import ru.otus.core.dao.Dao;
import ru.otus.core.model.User;
import ru.otus.core.service.AbstractDBService;
import ru.otus.core.service.DBService;

public class DbServiceUserImpl extends AbstractDBService<User, Long> implements DBService<User, Long> {

    public DbServiceUserImpl(Dao<User, Long> dao) {
        super(dao);
    }
}
