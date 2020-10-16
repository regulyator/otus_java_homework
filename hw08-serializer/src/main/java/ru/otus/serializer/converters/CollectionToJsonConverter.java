package ru.otus.serializer.converters;

import ru.otus.serializer.factorys.TypeConverterProvider;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class CollectionToJsonConverter implements ToJsonConverter {

    private final TypeConverterProvider valueConverterProcessor;

    public CollectionToJsonConverter(TypeConverterProvider valueConverterProcessor) {
        this.valueConverterProcessor = valueConverterProcessor;
    }

    @Override
    public String toJson(Object objectCollection) {
        return Objects.isNull(objectCollection) ? null : convertToString((Collection<?>) objectCollection);
    }

    private String convertToString(Collection<?> objectCollection) {
        return ARRAY_START_ELEMENT +
                objectCollection.stream()
                        .map(o -> valueConverterProcessor.getConverter(o.getClass()).toJson(o)).collect(Collectors.joining(",")) +
                ARRAY_END_ELEMENT;
    }
}
