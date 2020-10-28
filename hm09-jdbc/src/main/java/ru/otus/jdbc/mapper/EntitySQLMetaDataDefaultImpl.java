package ru.otus.jdbc.mapper;

import java.util.Objects;
import java.util.stream.Collectors;

public class EntitySQLMetaDataDefaultImpl<T> implements EntitySQLMetaData {

    private static final String SELECT_ALL_SQL_TEMPLATE = "select * from %s";
    private static final String SELECT_BY_ID_SQL_TEMPLATE = "select * from %s where %s = ?";
    private static final String INSERT_SQL_TEMPLATE = "insert into %s (%s) values (%s)";
    private static final String UPDATE_SQL_TEMPLATE = "update %s set %s where %s = ?";

    private final EntityClassMetaData<T> entityClassMetaData;

    public EntitySQLMetaDataDefaultImpl(EntityClassMetaData<T> entityClassMetaData) {
        if (Objects.isNull(entityClassMetaData)) {
            throw new IllegalArgumentException("entityClassMetaData can't be NULL!");
        }
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectAllSql() {
        return String.format(SELECT_ALL_SQL_TEMPLATE,
                entityClassMetaData.getName().toLowerCase());
    }

    @Override
    public String getSelectByIdSql() {
        return String.format(SELECT_BY_ID_SQL_TEMPLATE,
                entityClassMetaData.getName().toLowerCase(),
                entityClassMetaData.getIdField().getName().toLowerCase());
    }

    @Override
    public String getInsertSql() {
        return String.format(INSERT_SQL_TEMPLATE,
                entityClassMetaData.getName().toLowerCase(),
                generateFieldsToString(),
                generateValuesTemplate());
    }

    @Override
    public String getUpdateSql() {
        return String.format(UPDATE_SQL_TEMPLATE,
                entityClassMetaData.getName().toLowerCase(),
                generateFieldsValueParTemplate(),
                entityClassMetaData.getIdField().getName().toLowerCase());
    }

    private String generateFieldsToString() {
        return entityClassMetaData.getFieldsWithoutId().stream().map(field -> field.getName().toLowerCase()).collect(Collectors.joining(","));
    }

    private String generateValuesTemplate() {
        return entityClassMetaData.getFieldsWithoutId().stream().map(field -> "?").collect(Collectors.joining(","));
    }

    private String generateFieldsValueParTemplate() {
        return entityClassMetaData.getFieldsWithoutId().stream().map(field -> field.getName().toLowerCase() + " = ?").collect(Collectors.joining(","));
    }
}
