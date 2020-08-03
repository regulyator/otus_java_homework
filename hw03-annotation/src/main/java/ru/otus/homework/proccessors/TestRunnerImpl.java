package ru.otus.homework.proccessors;

import ru.otus.homework.proccessors.testproccesor.TestProcessor;

public class TestRunnerImpl implements TestRunner {

    private final TestProcessor testProcessor;

    public TestRunnerImpl(TestProcessor testProcessor) {
        this.testProcessor = testProcessor;
    }

    @Override
    public void prepare() {

    }

    @Override
    public void runTests() {

    }
}
