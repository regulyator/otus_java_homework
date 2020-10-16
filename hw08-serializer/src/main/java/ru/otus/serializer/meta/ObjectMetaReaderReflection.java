package ru.otus.serializer.meta;

import ru.otus.serializer.exception.ReadFieldException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ObjectMetaReaderReflection implements ObjectMetaReader {


    @Override
    public List<? extends FieldMeta> readObjectMeta(Object object) {
        if (Objects.isNull(object)) {
            return Collections.emptyList();
        }

        try {
            List<Field> fields = collectFields(object.getClass());
            List<FieldMeta> result = new ArrayList<>(fields.size());
            for (Field field : fields) {
                field.setAccessible(true);
                result.add(new FieldMeta(field.getName(), field.get(object), field.getType()));
            }

            return result;
        } catch (Exception ex) {
            throw new ReadFieldException(ex.getMessage(), ex);
        }

    }

    private List<Field> collectFields(Class<?> aClass) {
        if (Objects.isNull(aClass)) {
            return Collections.emptyList();
        }
        return Arrays.stream(aClass.getDeclaredFields())
                .collect(Collectors.toUnmodifiableList());

    }
}
