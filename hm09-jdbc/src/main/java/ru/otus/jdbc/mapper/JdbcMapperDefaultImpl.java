package ru.otus.jdbc.mapper;

import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutor;

public class JdbcMapperDefaultImpl<T> implements JdbcMapper<T> {

    private final SessionManager sessionManager;

    private final DbExecutor<T> executor;

    private final EntitySQLMetaData sqlMetaData;

    private final EntityClassMetaData<T> metaData;

    public JdbcMapperDefaultImpl(SessionManager sessionManager,
                                 DbExecutor<T> executor,
                                 EntitySQLMetaData sqlMetaData,
                                 EntityClassMetaData<T> metaData) {
        this.sessionManager = sessionManager;
        this.executor = executor;
        this.sqlMetaData = sqlMetaData;
        this.metaData = metaData;
    }


    @Override
    public void insert(T objectData) {

    }

    @Override
    public void update(T objectData) {

    }

    @Override
    public void insertOrUpdate(T objectData) {

    }

    @Override
    public T findById(Object id, Class<T> clazz) {
        return null;
    }
}
