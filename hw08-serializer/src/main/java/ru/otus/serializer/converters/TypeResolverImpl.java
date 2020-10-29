package ru.otus.serializer.converters;

import ru.otus.serializer.meta.Type;

import java.util.Collection;
import java.util.Set;

/**
 * для определения типов
 */
public class TypeResolverImpl implements TypeResolver {

    // враперы для премитивов отрабатываем так же как и сами премитивы
    private static final Set<Class<?>> WRAPPERS = Set.of(Boolean.class,
            Character.class,
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Float.class,
            Double.class);

    @Override
    public Type resolveType(Class<?> fieldType) {
        Type type;
        if (fieldType.isArray()) {
            type = Type.ARRAY;
        } else if (Collection.class.isAssignableFrom(fieldType)) {
            type = Type.COLLECTION;
        } else if (fieldType.isPrimitive() || TypeResolverImpl.WRAPPERS.contains(fieldType)) {
            type = Type.PRIMITIVE;
        } else if (fieldType.equals(String.class)) {
            type = Type.STRING;
        } else {
            type = Type.OBJECT;
        }
        return type;
    }
}
