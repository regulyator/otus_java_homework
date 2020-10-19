package ru.otus.serializer.converters;

import ru.otus.serializer.MyGson;
import ru.otus.serializer.factorys.TypeConverterProvider;

import java.util.Objects;

/**
 * конвертер для объектов
 */
public class ObjectToJsonConverter implements ToJsonConverter {

    private final TypeConverterProvider valueConverterProcessor;

    private final MyGson myGson;

    public ObjectToJsonConverter(TypeConverterProvider valueConverterProcessor, MyGson myGson) {
        this.valueConverterProcessor = valueConverterProcessor;
        this.myGson = myGson;
    }

    @Override
    public String toJson(Object object) {
        return Objects.isNull(object) ? "null" : myGson.toJson(object);
    }

}
