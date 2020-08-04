package ru.otus.homework.testworker.testrunner;

import ru.otus.homework.exeptions.*;
import ru.otus.homework.testworker.util.TestBatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static ru.otus.homework.testworker.util.reflection.ReflectionUtil.getClassInstance;
import static ru.otus.homework.testworker.util.reflection.ReflectionUtil.invokeMethod;

/**
 * раннер тестов
 * <p>
 * успешным считается тест который не выдал exception
 * И при получении экзэмпляра класса с тестом не выпал exception
 * И не выпал ни один из методов @Before и @After
 */
public class TestRunnerImpl implements TestRunner {
    // тут храним кол-во успешных тестов
    private int testSuccess;
    // тут храним кол-во упавших тестов
    private int testFail;


    /**
     * запускает все тесты из листа
     *
     * @param tests тесты
     * @return статистика
     */
    @Override
    public String runTests(List<TestBatch> tests) {
        testSuccess = 0;
        testFail = 0;

        Objects.requireNonNull(tests);

        tests.forEach(this::executeTest);

        return generateStatistic();

    }

    /**
     * запускает тест
     *
     * @param testBatch тест
     * @return статистика
     */
    @Override
    public String runTest(TestBatch testBatch) {
        this.runTests(Collections.singletonList(testBatch));
        return generateStatistic();
    }

    @Override
    public int getTestSuccess() {
        return testSuccess;
    }

    @Override
    public int getTestFail() {
        return testFail;
    }

    /**
     * непосредственно запуск тестов
     *
     * @param testBatch тест
     */
    private void executeTest(TestBatch testBatch) {
        int tmpTestFail = 0;
        int tmpTestSuccess = 0;

        for (Method testMethod : testBatch.getTestMethods()) {

            try {
                // получаем экзэмпляр класса
                Object testClassInstance = getClassInstance(testBatch.getTestClass());
                // это чтобы убедится что каждый тест идет в своём экзэмпляре класса
                System.out.println(testClassInstance);

                // выполняем методы @Before
                testBatch.getBeforeMethods().forEach(beforeMethod -> runBeforeMethod(beforeMethod, testClassInstance));

                try {
                    // выполняем сам тест
                    runTestMethod(testMethod, testClassInstance);
                    tmpTestSuccess++;
                } catch (TestMethodInvocationException | TestFailException ex) {
                    tmpTestFail++;
                }

                // выполняем методы @After
                testBatch.getAfterMethods().forEach(afterMethod -> runAfterMethod(afterMethod, testClassInstance));

                System.out.println("================================================");
            } catch (BeforeTestMethodInvocationException | AfterTestMethodInvocationException | TestClassInstantiationException ex) {
                // если вываливаемся на инстанцировании ИЛИ методах @Before @After - то считаем что все тесты в классе упали
                System.out.println("================================================");
                tmpTestFail = 0;
                tmpTestSuccess = 0;
                testFail += testBatch.getTestMethods().size();
                ex.printStackTrace();
                break;
            }


        }

        testSuccess += tmpTestSuccess;
        testFail += tmpTestFail;


    }

    private void runBeforeMethod(Method beforeMethod, Object testClassInstance) {
        try {
            invokeMethod(beforeMethod, testClassInstance);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new BeforeTestMethodInvocationException("Error when invoke BEFORE method!", e);
        }
    }

    private void runAfterMethod(Method afterMethod, Object testClassInstance) {
        try {
            invokeMethod(afterMethod, testClassInstance);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new AfterTestMethodInvocationException("Error when invoke AFTER method!", e);
        }
    }

    private void runTestMethod(Method testMethod, Object testClassInstance) {
        try {
            invokeMethod(testMethod, testClassInstance);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new TestMethodInvocationException("Test FAIL!", e);
        }
    }

    private String generateStatistic() {
        return String.format("Tests count ALL - %d, tests SUCCESS - %d, tests FAIL - %d,%n", testSuccess + testFail, testSuccess, testFail);
    }
}
