package ru.otus.data.core.service.impl;

import ru.otus.data.core.dao.Dao;
import ru.otus.data.core.model.Account;
import ru.otus.data.core.service.AbstractDBService;
import ru.otus.data.core.service.DBService;

public class DbServiceAccount extends AbstractDBService<Account, Long> implements DBService<Account, Long> {

    public DbServiceAccount(Dao<Account, Long> dao) {
        super(dao);
    }
}
