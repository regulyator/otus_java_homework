package ru.otus.serializer.meta;

public class FieldMeta {

    private final String fieldName;

    private final Object fieldValue;

    public FieldMeta(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
