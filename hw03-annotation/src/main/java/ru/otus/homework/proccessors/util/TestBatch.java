package ru.otus.homework.proccessors.util;

import java.lang.reflect.Method;
import java.util.List;

public class TestBatch {

    private List<Method> beforeMethods;
    private List<Method> afterMethods;
    private List<Method> testMethods;

    public TestBatch() {
    }

    public TestBatch(List<Method> beforeMethods, List<Method> afterMethods, List<Method> testMethods) {
        this.beforeMethods = beforeMethods;
        this.afterMethods = afterMethods;
        this.testMethods = testMethods;
    }

    public List<Method> getBeforeMethods() {
        return beforeMethods;
    }

    public void setBeforeMethods(List<Method> beforeMethods) {
        this.beforeMethods = beforeMethods;
    }

    public List<Method> getAfterMethods() {
        return afterMethods;
    }

    public void setAfterMethods(List<Method> afterMethods) {
        this.afterMethods = afterMethods;
    }

    public List<Method> getTestMethods() {
        return testMethods;
    }

    public void setTestMethods(List<Method> testMethods) {
        this.testMethods = testMethods;
    }
}
