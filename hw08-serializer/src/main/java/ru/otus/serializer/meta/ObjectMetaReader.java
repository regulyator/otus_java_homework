package ru.otus.serializer.meta;

import java.util.List;

public interface ObjectMetaReader {

    List<? extends FieldMeta> readObjectMeta(Object object);
}
