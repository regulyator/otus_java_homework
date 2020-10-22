package ru.otus.jdbc.mapper;

public class JdbcMapperDefaultImpl<T> implements JdbcMapper<T> {
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
