package ru.otus.data.core.service.impl;

import ru.otus.data.cachehw.HwCache;
import ru.otus.data.core.dao.Dao;
import ru.otus.data.core.model.User;
import ru.otus.data.core.service.AbstractCachedDBService;
import ru.otus.data.core.service.DBService;

public class DbCachedServiceUser extends AbstractCachedDBService<User, Long> implements DBService<User, Long> {

    public DbCachedServiceUser(Dao<User, Long> dao, HwCache<Long, User> userCache) {
        super(dao, userCache);
    }
}
