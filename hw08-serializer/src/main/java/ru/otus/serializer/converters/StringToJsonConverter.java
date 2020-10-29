package ru.otus.serializer.converters;

import java.util.Objects;

/**
 * конвертер для строки
 */
public class StringToJsonConverter implements ToJsonConverter {
    @Override
    public String toJson(Object object) {
        return Objects.isNull(object) ? "null" : String.format("%s%s%s", STRING_WRAPPER, object.toString(), STRING_WRAPPER);
    }
}
