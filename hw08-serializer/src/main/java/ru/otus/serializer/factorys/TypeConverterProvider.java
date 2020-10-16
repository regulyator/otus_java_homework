package ru.otus.serializer.factorys;

import ru.otus.serializer.converters.ToJsonConverter;

public interface TypeConverterProvider {

    ToJsonConverter getConverter(Class<?> aClass);
}
