package ru.otus.homework.testworker.testrunner;

import ru.otus.homework.testworker.util.TestBatch;

import java.util.List;

/**
 * раннер тестов
 */
public interface TestRunner {

    /**
     * запускает тесты из листа
     *
     * @param testBatch лист с тестами
     * @return возвращает строку со статистикой выполнения
     */
    String runTests(List<TestBatch> testBatch);

    /**
     * запускает одиночный тест
     *
     * @param testBatch тест
     * @return возвращает строку со статистикой выполнения
     */
    String runTest(TestBatch testBatch);

    /**
     * @return возвращает количество УСПЕШНЫХ тестов после последнего вызова runTests или runTest
     */
    int getTestSuccess();

    /**
     * @return возвращает количество УПАВШИХ тестов после последнего вызова runTests или runTest
     */
    int getTestFail();
}
