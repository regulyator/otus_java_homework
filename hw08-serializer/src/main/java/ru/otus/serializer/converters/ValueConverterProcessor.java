package ru.otus.serializer.converters;

import ru.otus.serializer.meta.FieldMeta;

public interface ValueConverterProcessor {

    String processValue(FieldMeta fieldMeta);
}
