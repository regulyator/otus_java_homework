package ru.otus.serializer.converters;

import ru.otus.serializer.meta.Type;

import java.util.Collection;
import java.util.Set;

public class TypeResolver {

    private static final Set<Class<?>> WRAPPERS = Set.of(Integer.class, Long.class,
            Byte.class, Short.class, Character.class, Float.class, Double.class, Boolean.class);

    public static Type resolveType(Class<?> fieldType) {
        Type type;
        if (fieldType.isArray()) {
            type = Type.ARRAY;
        } else if (fieldType.equals(Collection.class)) {
            type = Type.COLLECTION;
        } else if (fieldType.isPrimitive() || WRAPPERS.contains(fieldType)) {
            type = Type.PRIMITIVE;
        } else if (fieldType.equals(String.class)) {
            type = Type.STRING;
        } else {
            type = Type.OBJECT;
        }

        return type;
    }
}
