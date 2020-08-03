package ru.otus.homework.testworker.testrunner;

import ru.otus.homework.testworker.testproccesor.TestProcessor;
import ru.otus.homework.testworker.util.TestBatch;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TestRunnerImpl implements TestRunner {

    private final TestProcessor testProcessor;
    private List<TestBatch> tests;

    public TestRunnerImpl(TestProcessor testProcessor) {
        this.testProcessor = testProcessor;
    }

    @Override
    public void prepare() {
        this.tests = testProcessor.generateTestBatches();
    }

    @Override
    public void runTests() {
        tests.forEach(testBatch -> {
            testBatch.getTestMethods().forEach(testMethod -> {
                try {
                    Object testClass = testBatch.getTestClass().getDeclaredConstructor().newInstance();
                    System.out.println(testClass);
                    testBatch.getBeforeMethods().forEach(beforeMethod -> {
                        try {
                            beforeMethod.invoke(testClass);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });

                    testMethod.invoke(testClass);

                    testBatch.getAfterMethods().forEach(afterMethod -> {
                        try {
                            afterMethod.invoke(testClass);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });

                    System.out.println("================================================");

                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            });
        });

    }
}
