package ru.otus.serializer.converters;

public interface ToJsonConverter {

    String STRING_WRAPPER = "\"";
    String ARRAY_START_ELEMENT = "[";
    String ARRAY_END_ELEMENT = "]";

    String toJson(Object object);
}
