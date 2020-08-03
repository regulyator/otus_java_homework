package ru.otus.homework.proccessors.testproccesor;

import ru.otus.homework.proccessors.util.TestBatch;

import java.util.List;
import java.util.Map;

public interface TestProcessor {

    Map<String, Class> getAllTestClasses();

    List<TestBatch> generateTestBatches();
}
