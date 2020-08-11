package ru.otus.homework;

import ru.otus.homework.testworker.testproccesor.TestProcessor;
import ru.otus.homework.testworker.testproccesor.TestProcessorImpl;
import ru.otus.homework.testworker.testrunner.TestRunner;
import ru.otus.homework.testworker.testrunner.TestRunnerImpl;
import ru.otus.homework.testworker.util.TestBatch;

import java.util.List;

public class DIYTestFrameworkApp {

    public static void main(String[] args) {
        TestProcessor testProcessor = new TestProcessorImpl();
        List<TestBatch> tests = testProcessor.generateTestBatches();
        TestRunner testRunner = new TestRunnerImpl();
        System.out.println(testRunner.runTests(tests));
    }
}
