package ru.otus.serializer;

import ru.otus.serializer.converters.ValueConverterProcessor;
import ru.otus.serializer.meta.FieldMeta;
import ru.otus.serializer.meta.ObjectMetaReader;

import java.util.List;
import java.util.stream.Collectors;

public class MyGsonImpl implements MyGson {

    private final ObjectMetaReader objectMetaReader;
    private final ValueConverterProcessor valueConverterProcessor;

    public MyGsonImpl(ObjectMetaReader objectMetaReader, ValueConverterProcessor valueConverterProcessor) {
        this.objectMetaReader = objectMetaReader;
        this.valueConverterProcessor = valueConverterProcessor;
    }

    @Override
    public String toJson(Object object) {
        List<? extends FieldMeta> metaResult = objectMetaReader.readObjectMeta(object);
        return metaResult.stream().map(this::processField).collect(Collectors.joining(",", ROOT_START_ELEMENT, ROOT_END_ELEMENT));
    }

    private String processField(FieldMeta fieldMeta) {
        return "\"" + fieldMeta.getFieldName() + "\"" + ":" + valueConverterProcessor.processValue(fieldMeta);
    }
}
