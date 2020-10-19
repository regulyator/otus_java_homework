package ru.otus.serializer.factorys;

import ru.otus.serializer.MyGson;
import ru.otus.serializer.MyGsonImpl;
import ru.otus.serializer.converters.TypeResolverImpl;
import ru.otus.serializer.converters.ValueConverterProcessor;
import ru.otus.serializer.converters.ValueConverterProcessorImpl;
import ru.otus.serializer.meta.ObjectMetaReaderReflection;

public class MyGsonFactory {

    public static MyGson getDefaultMyGsonConverter() {
        return new MyGsonImpl(new ObjectMetaReaderReflection(), getValueProcessor());
    }

    private static ValueConverterProcessor getValueProcessor() {
        return new ValueConverterProcessorImpl(new DefaultTypeConverterProvider(new TypeResolverImpl()));
    }
}
