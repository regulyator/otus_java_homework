package ru.otus;

public class SampleObject {

    private int someIntField;

    private Integer someIntegerField;

    private String someStringField;

    public SampleObject(int someIntField, Integer someIntegerField, String someStringField) {
        this.someIntField = someIntField;
        this.someIntegerField = someIntegerField;
        this.someStringField = someStringField;
    }

    public int getSomeIntField() {
        return someIntField;
    }

    public void setSomeIntField(int someIntField) {
        this.someIntField = someIntField;
    }

    public Integer getSomeIntegerField() {
        return someIntegerField;
    }

    public void setSomeIntegerField(Integer someIntegerField) {
        this.someIntegerField = someIntegerField;
    }

    public String getSomeStringField() {
        return someStringField;
    }

    public void setSomeStringField(String someStringField) {
        this.someStringField = someStringField;
    }
}
