package ru.otus.homework.testworker.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * класс для хранения пакетов с тестами
 * testClass - сам класс темта
 * beforeMethods - методы @Before
 * afterMethods - методы @After
 * testMethods - методы @Test
 */
public class TestBatch {

    private final Class<?> testClass;
    private List<Method> beforeMethods;
    private List<Method> afterMethods;
    private List<Method> testMethods;

    public TestBatch(Class<?> testClass) {
        this.testClass = testClass;
    }

    public TestBatch(List<Method> beforeMethods,
                     List<Method> afterMethods,
                     List<Method> testMethods,
                     Class<?> testClass) {
        this.beforeMethods = beforeMethods;
        this.afterMethods = afterMethods;
        this.testMethods = testMethods;
        this.testClass = testClass;
    }

    public List<Method> getBeforeMethods() {
        return Objects.isNull(beforeMethods) ? beforeMethods = new ArrayList<>() : beforeMethods;
    }

    public void setBeforeMethods(List<Method> beforeMethods) {
        this.beforeMethods = beforeMethods;
    }

    public List<Method> getAfterMethods() {
        return Objects.isNull(afterMethods) ? afterMethods = new ArrayList<>() : afterMethods;
    }

    public void setAfterMethods(List<Method> afterMethods) {
        this.afterMethods = afterMethods;
    }

    public List<Method> getTestMethods() {
        return Objects.isNull(testMethods) ? testMethods = new ArrayList<>() : testMethods;
    }

    public void setTestMethods(List<Method> testMethods) {
        this.testMethods = testMethods;
    }

    public Class<?> getTestClass() {
        return testClass;
    }
}
