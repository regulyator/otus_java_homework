package ru.otus.data.core.service.impl;

import ru.otus.data.core.dao.Dao;
import ru.otus.data.core.model.User;
import ru.otus.data.core.service.AbstractDBService;
import ru.otus.data.core.service.DBService;

public class DbServiceUser extends AbstractDBService<User, Long> implements DBService<User, Long> {

    public DbServiceUser(Dao<User, Long> dao) {
        super(dao);
    }
}
