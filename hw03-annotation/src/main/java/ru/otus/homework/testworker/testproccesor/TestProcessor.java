package ru.otus.homework.testworker.testproccesor;

import ru.otus.homework.testworker.util.TestBatch;

import java.util.List;
import java.util.Map;

public interface TestProcessor {

    Map<String, Class> getAllTestClasses();

    List<TestBatch> generateTestBatches();
}
