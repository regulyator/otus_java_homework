package ru.otus.serializer.factorys;

import ru.otus.serializer.converters.*;
import ru.otus.serializer.meta.Type;

import java.util.HashMap;
import java.util.Map;

public class DefaultTypeConverterProvider implements TypeConverterProvider {

    private final Map<Type, ToJsonConverter> typeConvertersProvider = new HashMap<>(Type.values().length);

    @Override
    public ToJsonConverter getConverter(Class<?> aClass) {
        return typeConvertersProvider.computeIfAbsent(TypeResolver.resolveType(aClass), this::createConverter);
    }

    private ToJsonConverter createConverter(Type type) {
        return switch (type) {
            case PRIMITIVE -> new PrimitiveToJsonConverter();
            case ARRAY -> new ArrayToJsonConverter(this);
            case COLLECTION -> new CollectionToJsonConverter(this);
            case OBJECT -> new ObjectToJsonConverter(this);
            case STRING -> new StringToJsonConverter();

        };
    }
}
