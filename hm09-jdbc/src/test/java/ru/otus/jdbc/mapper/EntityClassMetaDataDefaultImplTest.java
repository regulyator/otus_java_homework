package ru.otus.jdbc.mapper;

import org.junit.jupiter.api.Test;
import ru.otus.data.core.exceptions.GetEntityClassMetaDataException;
import ru.otus.jdbc.mapper.testclasses.User;
import ru.otus.jdbc.mapper.testclasses.UserWithoutDefaultConstructor;
import ru.otus.jdbc.mapper.testclasses.UserWithoutFields;
import ru.otus.jdbc.mapper.testclasses.UserWithoutId;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class EntityClassMetaDataDefaultImplTest {

    @Test
    void testGenerateMetaData() {
        EntityClassMetaData<User> userEntityClassMetaData = new EntityClassMetaDataDefaultImpl<>(User.class);

        assertEquals("User", userEntityClassMetaData.getName());

        assertEquals(3, userEntityClassMetaData.getAllFields().size());
        assertEquals(2, userEntityClassMetaData.getFieldsWithoutId().size());

        try {
            Field idField = User.class.getDeclaredField("id");
            assertEquals(idField, userEntityClassMetaData.getIdField());
        } catch (NoSuchFieldException e) {
            fail(e);
        }

        try {
            Constructor<User> defaultUserConstructor = User.class.getConstructor();
            assertEquals(defaultUserConstructor, userEntityClassMetaData.getConstructor());
        } catch (NoSuchMethodException e) {
            fail(e);
        }
    }

    @Test
    void testWithoutId() {
        assertThrows(GetEntityClassMetaDataException.class, () -> new EntityClassMetaDataDefaultImpl<>(UserWithoutId.class));
    }

    @Test
    void testWithoutDefaultConstructor() {
        assertThrows(GetEntityClassMetaDataException.class, () -> new EntityClassMetaDataDefaultImpl<>(UserWithoutDefaultConstructor.class));
    }

    @Test
    void testWithoutFields() {
        assertThrows(GetEntityClassMetaDataException.class, () -> new EntityClassMetaDataDefaultImpl<>(UserWithoutFields.class));
    }
}