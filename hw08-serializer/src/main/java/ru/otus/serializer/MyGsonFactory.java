package ru.otus.serializer;

import ru.otus.serializer.meta.ObjectMetaReaderReflection;

public class MyGsonFactory {

    public static MyGson getDefaultMyGsonConverter() {
        return new MyGsonImpl(new ObjectMetaReaderReflection());
    }
}
