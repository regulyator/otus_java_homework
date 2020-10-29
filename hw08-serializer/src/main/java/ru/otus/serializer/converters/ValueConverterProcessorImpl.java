package ru.otus.serializer.converters;

import ru.otus.serializer.exception.FieldConvertException;
import ru.otus.serializer.factorys.TypeConverterProvider;
import ru.otus.serializer.meta.FieldMeta;

import java.util.Objects;

/**
 * обработчик полей
 */
public class ValueConverterProcessorImpl implements ValueConverterProcessor {

    private final TypeConverterProvider typeConvertersProvider;

    public ValueConverterProcessorImpl(TypeConverterProvider typeConvertersProvider) {
        this.typeConvertersProvider = typeConvertersProvider;
    }

    @Override
    public String processValue(FieldMeta fieldMeta) {
        ToJsonConverter typeConverter = typeConvertersProvider.getConverter(fieldMeta.getFieldType());
        if (Objects.isNull(typeConverter)) {
            throw new FieldConvertException("No converter for type! type: " + fieldMeta.getFieldType());
        } else {
            return typeConverter.toJson(fieldMeta.getFieldValue());
        }
    }
}
