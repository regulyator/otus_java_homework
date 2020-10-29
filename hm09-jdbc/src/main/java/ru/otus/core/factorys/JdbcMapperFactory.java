package ru.otus.core.factorys;

import ru.otus.core.util.ReflectionUtils;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.mapper.*;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

public class JdbcMapperFactory {
    private final SessionManagerJdbc sessionManager;
    private final ReflectionUtils reflectionUtils = new ReflectionUtils();

    public JdbcMapperFactory(SessionManagerJdbc sessionManager) {
        this.sessionManager = sessionManager;
    }

    public <T> JdbcMapper<T> createJdbcMapper(Class<T> clazz, DbExecutor<T> dbExecutor) {
        EntityClassMetaData<T> entityClassMetaData = new EntityClassMetaDataDefaultImpl<>(clazz);
        return new JdbcMapperDefaultImpl<>(this.sessionManager,
                dbExecutor,
                new EntitySQLMetaDataDefaultImpl<>(entityClassMetaData),
                entityClassMetaData, reflectionUtils);
    }
}
