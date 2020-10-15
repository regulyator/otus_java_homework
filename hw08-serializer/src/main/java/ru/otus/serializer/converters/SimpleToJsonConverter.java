package ru.otus.serializer.converters;

import java.util.Objects;

public class SimpleToJsonConverter implements ToJsonConverter {

    private final String wrapperString;

    public SimpleToJsonConverter() {
        this.wrapperString = DEFAULT_WRAPPER_STRING;
    }

    public SimpleToJsonConverter(String wrapperString) {
        this.wrapperString = wrapperString;
    }

    @Override
    public String toJson(Object object) {
        return Objects.isNull(object) ? "null" : String.format("%s%s%s", wrapperString, object, wrapperString);
    }
}
