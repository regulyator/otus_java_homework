package ru.otus.serializer.converters;

import java.util.Objects;

/**
 * конвертер примитивов и их враперов
 */
public class PrimitiveToJsonConverter implements ToJsonConverter {

    @Override
    public String toJson(Object object) {
        return Objects.isNull(object) ? "null" :
                getStringValue(object);
    }

    private String getStringValue(Object object) {
        return object.getClass().equals(Character.class) ?
                String.format("%s%s%s", STRING_WRAPPER, object.toString(), STRING_WRAPPER) : object.toString();
    }
}
