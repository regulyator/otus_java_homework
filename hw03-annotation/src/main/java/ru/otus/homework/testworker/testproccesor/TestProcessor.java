package ru.otus.homework.testworker.testproccesor;

import ru.otus.homework.testworker.util.TestBatch;

import java.util.List;
import java.util.Map;

/**
 * обработчик для нахождения классов тестов
 */
public interface TestProcessor {

    /**
     * сканер классов с тестами
     *
     * @return возвращает мапу с canonicalName классов с тестами и сам Class
     */
    Map<String, Class<?>> getAllTestClasses();

    /**
     * генератор "пакетов" с тестами
     *
     * @return возвращает лист с "пакетами" для тестов
     */
    List<TestBatch> generateTestBatches();
}
