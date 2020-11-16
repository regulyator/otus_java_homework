package ru.otus.data.core.dao.impl;

import ru.otus.data.core.dao.AccountDao;
import ru.otus.data.core.model.Account;
import ru.otus.data.core.sessionmanager.SessionManager;
import ru.otus.jdbc.mapper.JdbcMapper;

import java.util.Optional;

public class AccountDaoImpl implements AccountDao {

    private final JdbcMapper<Account> accountJdbcMapper;

    public AccountDaoImpl(JdbcMapper<Account> userJdbcMapper) {
        this.accountJdbcMapper = userJdbcMapper;
    }


    @Override
    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(accountJdbcMapper.findById(id, Account.class));
    }

    @Override
    public long insertUser(Account account) {
        accountJdbcMapper.insert(account);
        return account.getNo();
    }

    @Override
    public void updateUser(Account account) {
        accountJdbcMapper.update(account);
    }

    @Override
    public void insertOrUpdate(Account account) {
        accountJdbcMapper.insertOrUpdate(account);
    }

    @Override
    public SessionManager getSessionManager() {
        return accountJdbcMapper.getSessionManager();
    }
}
