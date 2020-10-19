package ru.otus.serializer.factorys;

import ru.otus.serializer.converters.*;
import ru.otus.serializer.meta.Type;

import java.util.HashMap;
import java.util.Map;

/**
 * маппинг типов и их конвертеров
 */
public class DefaultTypeConverterProvider implements TypeConverterProvider {

    private final Map<Type, ToJsonConverter> typeConvertersProvider = new HashMap<>(Type.values().length);
    private final TypeResolver typeResolver;

    public DefaultTypeConverterProvider(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Override
    public ToJsonConverter getConverter(Class<?> aClass) {
        return typeConvertersProvider.computeIfAbsent(typeResolver.resolveType(aClass), this::createConverter);
    }

    private ToJsonConverter createConverter(Type type) {
        return switch (type) {
            case PRIMITIVE -> new PrimitiveToJsonConverter();
            case ARRAY -> new ArrayToJsonConverter(this);
            case COLLECTION -> new CollectionToJsonConverter(this);
            case OBJECT -> new ObjectToJsonConverter(this, MyGsonFactory.getDefaultMyGsonConverter());
            case STRING -> new StringToJsonConverter();
        };
    }
}
