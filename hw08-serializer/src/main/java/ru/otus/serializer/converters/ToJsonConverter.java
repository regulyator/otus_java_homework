package ru.otus.serializer.converters;

public interface ToJsonConverter {

    String DEFAULT_WRAPPER_STRING = "";

    String toJson(Object object);
}
