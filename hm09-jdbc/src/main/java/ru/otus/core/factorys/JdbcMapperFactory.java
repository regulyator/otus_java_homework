package ru.otus.core.factorys;

import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.mapper.*;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

public class JdbcMapperFactory {
    private final SessionManagerJdbc sessionManager;

    public JdbcMapperFactory(SessionManagerJdbc sessionManager) {
        this.sessionManager = sessionManager;
    }

    public <T> JdbcMapper<T> createJdbcMapper(Class<T> clazz, DbExecutor<T> dbExecutor) {
        EntityClassMetaData<T> entityClassMetaData = new EntityClassMetaDataDefaultImpl<>(clazz);
        return new JdbcMapperDefaultImpl<T>(this.sessionManager,
                dbExecutor,
                new EntitySQLMetaDataDefaultImpl<T>(entityClassMetaData),
                entityClassMetaData);
    }
}
