package ru.otus.jdbc.mapper;

import ru.otus.core.exceptions.*;
import ru.otus.core.util.ReflectionUtils;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcMapperDefaultImpl<T> implements JdbcMapper<T> {

    private final SessionManagerJdbc sessionManager;

    private final DbExecutor<T> executor;

    private final EntitySQLMetaData sqlMetaData;

    private final EntityClassMetaData<T> metaData;

    public JdbcMapperDefaultImpl(SessionManagerJdbc sessionManager,
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
        try {
            long generatedId = executor.executeInsert(retrieveConnection(), sqlMetaData.getInsertSql(), retrieveInsertParams(objectData));
            setIdField(generatedId, objectData);
        } catch (SQLException ex) {
            throw new InsertException("Error when insert entity!", ex);
        }
    }

    @Override
    public void update(T objectData) {
        try {
            executor.executeUpdate(retrieveConnection(), sqlMetaData.getUpdateSql(), retrieveUpdateParams(objectData));
        } catch (SQLException ex) {
            throw new UpdateException("Error when update entity!", ex);
        }

    }

    @Override
    public void insertOrUpdate(T objectData) {
        if (idFieldIsNull(objectData)) {
            this.insert(objectData);
        } else {
            this.update(objectData);
        }
    }

    @Override
    public T findById(Object id, Class<T> clazz) {
        try {
            return executor.executeSelect(retrieveConnection(), sqlMetaData.getSelectByIdSql(), id, this::fillEntity).orElse(null);
        } catch (SQLException ex) {
            throw new UpdateException("Error when select entity by id!", ex);
        }
    }

    @Override
    public SessionManagerJdbc getSessionManager() {
        return sessionManager;
    }

    private T fillEntity(ResultSet resultSet) {
        try {
            T entity = metaData.getConstructor().newInstance();
            for (Field field : metaData.getAllFields()) {
                ReflectionUtils.setField(entity, field, resultSet.getObject(field.getName()));
            }
            return entity;
        } catch (Exception ex) {
            throw new FillEntityException("Error when fill entity!", ex);
        }
    }

    private Connection retrieveConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }

    private void setIdField(Object generatedId, T objectData) {
        try {
            ReflectionUtils.setField(objectData, metaData.getIdField(), generatedId);
        } catch (IllegalAccessException ex) {
            throw new SetIdFieldException("Error when set entity id field value!", ex);
        }
    }

    private boolean idFieldIsNull(T objectData) {
        return ReflectionUtils.checkFieldIsNull(objectData, metaData.getIdField());
    }

    private List<Object> retrieveUpdateParams(T objectData) {
        try {
            return ReflectionUtils.getObjectFieldValues(objectData, metaData.getAllFields());
        } catch (IllegalAccessException ex) {
            throw new RetrieveObjectFieldsValuesException("Error when get entity fields values!", ex);
        }
    }

    private List<Object> retrieveInsertParams(T objectData) {
        try {
            return ReflectionUtils.getObjectFieldValues(objectData, metaData.getFieldsWithoutId());
        } catch (IllegalAccessException ex) {
            throw new RetrieveObjectFieldsValuesException("Error when get entity fields values!", ex);
        }
    }
}
