package ru.otus.serializer;

public interface MyGson {

    String ROOT_START_ELEMENT = "{";
    String ROOT_END_ELEMENT = "}";

    String toJson(Object object);
}
