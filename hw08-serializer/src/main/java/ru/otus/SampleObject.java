package ru.otus;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class SampleObject {

    private final int someIntField;

    private final byte someByteField;

    private final char someCharField;

    private final boolean someBooleanPrimitiveField;

    private final char[] someCharArrayField;

    private final Boolean someBooleanField;

    private final Integer someIntegerField;

    private final String someStringField;

    private final Collection<?> someCollection;

    private final SampleObject someObject;


    public SampleObject(int someIntField, byte someByteField, char someCharField, boolean someBooleanPrimitiveField, char[] someCharArrayField, Boolean someBooleanField, Integer someIntegerField, String someStringField, Collection<?> someCollection, SampleObject someObject) {
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

    public Collection<?> getSomeCollection() {
        return someCollection;
    }

    public SampleObject getSomeObject() {
        return someObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampleObject that = (SampleObject) o;
        return someIntField == that.someIntField &&
                someByteField == that.someByteField &&
                someCharField == that.someCharField &&
                someBooleanPrimitiveField == that.someBooleanPrimitiveField &&
                Arrays.equals(someCharArrayField, that.someCharArrayField) &&
                Objects.equals(someBooleanField, that.someBooleanField) &&
                Objects.equals(someIntegerField, that.someIntegerField) &&
                Objects.equals(someStringField, that.someStringField) &&
                Objects.equals(someCollection, that.someCollection) &&
                Objects.equals(someObject, that.someObject);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(someIntField, someByteField, someCharField, someBooleanPrimitiveField, someBooleanField, someIntegerField, someStringField, someCollection, someObject);
        result = 31 * result + Arrays.hashCode(someCharArrayField);
        return result;
    }
}
