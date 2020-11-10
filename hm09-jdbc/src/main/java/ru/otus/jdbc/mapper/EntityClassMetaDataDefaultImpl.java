package ru.otus.jdbc.mapper;

import ru.otus.core.annotations.Column;
import ru.otus.core.annotations.Id;
import ru.otus.core.annotations.Table;
import ru.otus.core.exceptions.GetEntityClassMetaDataException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityClassMetaDataDefaultImpl<T> implements EntityClassMetaData<T> {

    private String entityName;
    private Constructor<T> defaultConstructor;
    private Field idField;
    private List<Field> entityFieldsWithoutIdField = new ArrayList<>();

    public EntityClassMetaDataDefaultImpl(Class<T> tClass) {
        if (Objects.isNull(tClass)) {
            throw new IllegalArgumentException("Entity class can't be NULL!");
        }
        scanClass(tClass);
    }

    @Override
    public String getName() {
        return this.entityName;
    }

    @Override
    public Constructor<T> getConstructor() {
        return this.defaultConstructor;
    }

    @Override
    public Field getIdField() {
        return this.idField;
    }

    @Override
    public List<Field> getAllFields() {
        return Stream.concat(entityFieldsWithoutIdField.stream(), Stream.of(idField)).collect(Collectors.toList());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return this.entityFieldsWithoutIdField.stream().collect(Collectors.toList());
    }

    private void scanClass(Class<T> tClass) {
        try {
            getEntityName(tClass);
            getEntityDefaultConstructor(tClass);
            getEntityFields(tClass);
        } catch (Exception ex) {
            throw new GetEntityClassMetaDataException("Error when process entity class meta!");
        }
    }

    private void getEntityName(Class<T> tClass) {
        if (tClass.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = tClass.getAnnotation(Table.class);
            String annotationTableName = tableAnnotation.tableName();
            this.entityName = annotationTableName.isBlank() ? tClass.getSimpleName() : annotationTableName;
        } else {
            this.entityName = tClass.getSimpleName();
        }
    }

    private void getEntityDefaultConstructor(Class<T> tClass) throws NoSuchMethodException {
        this.defaultConstructor = tClass.getConstructor();

    }

    private void getEntityFields(Class<T> tClass) {
        Arrays.stream(tClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Column.class)
                || field.isAnnotationPresent(Id.class)).forEach(field -> {
            if (field.isAnnotationPresent(Id.class)) {
                this.idField = field;
            } else if (field.isAnnotationPresent(Column.class)) {
                entityFieldsWithoutIdField.add(field);
            }
        });
        if (this.entityFieldsWithoutIdField.size() == 0 || Objects.isNull(this.idField)) {
            throw new GetEntityClassMetaDataException("Error when process entity class meta!");
        }

    }
}
