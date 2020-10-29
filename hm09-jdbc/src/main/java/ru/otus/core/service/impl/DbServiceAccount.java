package ru.otus.core.service.impl;

import ru.otus.core.dao.Dao;
import ru.otus.core.model.Account;
import ru.otus.core.service.AbstractDBService;
import ru.otus.core.service.DBService;

public class DbServiceAccount extends AbstractDBService<Account, Long> implements DBService<Account, Long> {

    public DbServiceAccount(Dao<Account, Long> dao) {
        super(dao);
    }
}
