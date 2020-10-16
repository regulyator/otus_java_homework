package ru.otus.serializer.converters;

import ru.otus.serializer.factorys.TypeConverterProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArrayToJsonConverter implements ToJsonConverter {

    private final TypeConverterProvider valueConverterProcessor;

    public ArrayToJsonConverter(TypeConverterProvider valueConverterProcessor) {
        this.valueConverterProcessor = valueConverterProcessor;
    }

    @Override
    public String toJson(Object objectArray) {
        return Objects.isNull(objectArray) ? null : convertToString(objectArray);
    }

    private String convertToString(Object objectArray) {
        List<Object> objectList = arrayToList(objectArray);
        return ARRAY_START_ELEMENT +
                objectList.stream()
                        .map(o -> valueConverterProcessor.getConverter(o.getClass()).toJson(o)).collect(Collectors.joining(",")) +
                ARRAY_END_ELEMENT;
    }

    private List<Object> arrayToList(Object objectArray) {
        final List<Object> resultList = new ArrayList<>();
        for (int i = 0; i < Array.getLength(objectArray); i++)
            resultList.add(Array.get(objectArray, i));

        return resultList;
    }
}
