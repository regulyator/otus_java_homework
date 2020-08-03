package ru.otus.homework.testworker.util;

import java.lang.reflect.Method;
import java.util.List;

public class TestBatch {

    private List<Method> beforeMethods;
    private List<Method> afterMethods;
    private List<Method> testMethods;
    private final Class testClass;

    public TestBatch(Class testClass) {
        this.testClass = testClass;
    }

    public TestBatch(List<Method> beforeMethods,
                     List<Method> afterMethods,
                     List<Method> testMethods,
                     Class testClass) {
        this.beforeMethods = beforeMethods;
        this.afterMethods = afterMethods;
        this.testMethods = testMethods;
        this.testClass = testClass;
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

    public Class getTestClass() {
        return testClass;
    }
}
