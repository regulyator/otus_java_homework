package ru.otus.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.Dao;
import ru.otus.core.model.Account;
import ru.otus.core.service.AbstractDBService;
import ru.otus.core.service.DBService;

import java.util.Optional;

public class DbServiceAccount extends AbstractDBService<Account, Long> implements DBService<Account, Long> {

    public DbServiceAccount(Dao<Account, Long> dao) {
        super(dao);
    }
}
