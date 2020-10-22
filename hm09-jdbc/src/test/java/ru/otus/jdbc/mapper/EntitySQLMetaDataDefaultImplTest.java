package ru.otus.jdbc.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.jdbc.mapper.testclasses.User;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EntitySQLMetaDataDefaultImplTest {
    private static String entityName;
    private static Field idField;
    private static List<Field> fields;
    private static List<Field> fieldsWithoutId;
    @Mock
    EntityClassMetaData<User> entityClassMetaData;


    @BeforeAll
    static void initTestData() {
        try {
            entityName = User.class.getSimpleName();
            idField = User.class.getDeclaredField("id");
            fields = Arrays.asList(User.class.getDeclaredFields().clone());
            fieldsWithoutId = Arrays.stream(User.class.getDeclaredFields().clone()).filter(field -> !Objects.equals(idField, field)).collect(Collectors.toList());
        } catch (NoSuchFieldException e) {
            fail();
        }


    }

    @Test
    void getSelectAllSql() {
        when(entityClassMetaData.getName()).thenReturn(entityName);

        EntitySQLMetaData entitySQLMetaData = new EntitySQLMetaDataDefaultImpl<>(entityClassMetaData);

        assertEquals("select * from user", entitySQLMetaData.getSelectAllSql());
    }

    @Test
    void getSelectByIdSql() {
        when(entityClassMetaData.getName()).thenReturn(entityName);
        when(entityClassMetaData.getIdField()).thenReturn(idField);

        EntitySQLMetaData entitySQLMetaData = new EntitySQLMetaDataDefaultImpl<>(entityClassMetaData);

        assertEquals("select * from user where id = ?", entitySQLMetaData.getSelectByIdSql());
    }

    @Test
    void getInsertSql() {
        when(entityClassMetaData.getName()).thenReturn(entityName);
        when(entityClassMetaData.getAllFields()).thenReturn(fields);

        EntitySQLMetaData entitySQLMetaData = new EntitySQLMetaDataDefaultImpl<>(entityClassMetaData);

        assertEquals("insert into user (id,name,age) values (?,?,?)", entitySQLMetaData.getInsertSql());
    }

    @Test
    void getUpdateSql() {
        when(entityClassMetaData.getName()).thenReturn(entityName);
        when(entityClassMetaData.getIdField()).thenReturn(idField);
        when(entityClassMetaData.getFieldsWithoutId()).thenReturn(fieldsWithoutId);

        EntitySQLMetaData entitySQLMetaData = new EntitySQLMetaDataDefaultImpl<>(entityClassMetaData);

        assertEquals("update user set name = ?,age = ? where id = ?", entitySQLMetaData.getUpdateSql());
    }
}