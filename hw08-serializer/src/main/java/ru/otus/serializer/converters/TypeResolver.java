package ru.otus.serializer.converters;

import ru.otus.serializer.meta.Type;

public interface TypeResolver {
    Type resolveType(Class<?> fieldType);
}
