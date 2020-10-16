package ru.otus;

import java.util.Collection;

public class SampleObject {

    private final int someIntField;

    private final byte someByteField;

    private final char someCharField;

    private final boolean someBooleanPrimitiveField;

    private final char[] someCharArrayField;

    private final Boolean someBooleanField;

    private final Integer someIntegerField;

    private final String someStringField;

    private final Collection someCollection;

    private final Object someObject;


    public SampleObject(int someIntField, byte someByteField, char someCharField, boolean someBooleanPrimitiveField, char[] someCharArrayField, Boolean someBooleanField, Integer someIntegerField, String someStringField, Collection someCollection, Object someObject) {
        this.someIntField = someIntField;
        this.someByteField = someByteField;
        this.someCharField = someCharField;
        this.someBooleanPrimitiveField = someBooleanPrimitiveField;
        this.someCharArrayField = someCharArrayField;
        this.someBooleanField = someBooleanField;
        this.someIntegerField = someIntegerField;
        this.someStringField = someStringField;
        this.someCollection = someCollection;
        this.someObject = someObject;
    }

    public int getSomeIntField() {
        return someIntField;
    }

    public byte getSomeByteField() {
        return someByteField;
    }

    public char getSomeCharField() {
        return someCharField;
    }

    public boolean isSomeBooleanPrimitiveField() {
        return someBooleanPrimitiveField;
    }

    public char[] getSomeCharArrayField() {
        return someCharArrayField;
    }

    public Boolean getSomeBooleanField() {
        return someBooleanField;
    }

    public Integer getSomeIntegerField() {
        return someIntegerField;
    }

    public String getSomeStringField() {
        return someStringField;
    }

    public Collection getSomeCollection() {
        return someCollection;
    }

    public Object getSomeObject() {
        return someObject;
    }
}
