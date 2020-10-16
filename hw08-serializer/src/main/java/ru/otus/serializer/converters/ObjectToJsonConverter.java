package ru.otus.serializer.converters;

import ru.otus.serializer.factorys.TypeConverterProvider;

public class ObjectToJsonConverter implements ToJsonConverter {

    private final TypeConverterProvider valueConverterProcessor;

    public ObjectToJsonConverter(TypeConverterProvider valueConverterProcessor) {
        this.valueConverterProcessor = valueConverterProcessor;
    }

    @Override
    public String toJson(Object object) {
        return null;
    }

}
