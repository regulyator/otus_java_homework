package ru.otus.serializer.meta;

public class FieldMeta {

    private final String fieldName;
    private final Object fieldValue;
    private final Object fieldType;


    public FieldMeta(String fieldName, Object fieldValue, Class<?> fieldType) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public Object getFieldType() {
        return fieldType;
    }
}
