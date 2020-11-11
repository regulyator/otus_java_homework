package ru.otus.core.service.impl;

import ru.otus.cachehw.HwCache;
import ru.otus.core.dao.Dao;
import ru.otus.core.model.User;
import ru.otus.core.service.AbstractCachedDBService;
import ru.otus.core.service.DBService;

public class DbCachedServiceUser extends AbstractCachedDBService<User, Long> implements DBService<User, Long> {

    public DbCachedServiceUser(Dao<User, Long> dao, HwCache<Long, User> userCache) {
        super(dao, userCache);
    }
}
