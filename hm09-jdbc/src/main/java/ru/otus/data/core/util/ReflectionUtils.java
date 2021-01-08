package ru.otus.data.core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReflectionUtils {

    public List<Object> getObjectFieldValues(Object o, List<Field> fields) throws IllegalAccessException {
        List<Object> result = new ArrayList<>(fields.size());
        for (Field field : fields) {
            field.setAccessible(true);
            result.add(field.get(o));
        }
        return result;
    }

    public void setField(Object o, Field field, Object value) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(o, value);
    }

    public boolean checkFieldIsNull(Object o, Field field) {
        try {
            field.setAccessible(true);
            return Objects.isNull(field.get(o));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
