package ru.otus.homework.testworker.testrunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.homework.testfortest.DIYTest1;
import ru.otus.homework.testfortest.DIYTest2;
import ru.otus.homework.testfortest.DIYTest4;
import ru.otus.homework.testworker.util.TestBatch;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TestRunnerImplTest {

    private TestRunner testRunner;
    private List<TestBatch> testBatches;
    private TestBatch testBatch1;
    private TestBatch testBatch2;
    private TestBatch testBatch4;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        initTestBatches();
        testRunner = new TestRunnerImpl();


    }


    @Test
    void checkRunTest() {
        String result1 = testRunner.runTest(testBatch1);

        assertFalse(result1.isEmpty());

        assertEquals(testRunner.getTestFail(), 1);
        assertEquals(testRunner.getTestSuccess(), 0);

        String result2 = testRunner.runTest(testBatch2);

        assertFalse(result2.isEmpty());

        assertEquals(testRunner.getTestFail(), 2);
        assertEquals(testRunner.getTestSuccess(), 0);

        String result4 = testRunner.runTest(testBatch4);

        assertFalse(result4.isEmpty());

        assertEquals(testRunner.getTestFail(), 1);
        assertEquals(testRunner.getTestSuccess(), 1);

    }

    @Test
    void checkRunTests() {
        String result = testRunner.runTests(testBatches);

        assertFalse(result.isEmpty());

        assertEquals(testRunner.getTestFail(), 4);
        assertEquals(testRunner.getTestSuccess(), 1);
    }

    private void initTestBatches() throws NoSuchMethodException {
        this.testBatch1 = new TestBatch(DIYTest1.class);

        this.testBatch1.setBeforeMethods(Arrays.asList(DIYTest1.class.getMethod("doBefore1")));
        this.testBatch1.setTestMethods(Arrays.asList(DIYTest1.class.getMethod("doTest1")));
        this.testBatch1.setAfterMethods(Arrays.asList(DIYTest1.class.getMethod("doAfter1")));

        this.testBatch2 = new TestBatch(DIYTest2.class);

        this.testBatch2.setBeforeMethods(Arrays.asList(DIYTest2.class.getMethod("doBefore1"), DIYTest2.class.getMethod("doBefore2")));
        this.testBatch2.setTestMethods(Arrays.asList(DIYTest2.class.getMethod("doTest1"), DIYTest2.class.getMethod("doTest2")));
        this.testBatch2.setAfterMethods(Arrays.asList(DIYTest2.class.getMethod("doAfter1"), DIYTest2.class.getMethod("doAfter2")));

        this.testBatch4 = new TestBatch(DIYTest4.class);

        this.testBatch4.setTestMethods(Arrays.asList(DIYTest4.class.getMethod("doTest1"), DIYTest4.class.getMethod("doTest2")));

        this.testBatches = Arrays.asList(testBatch1, testBatch2, testBatch4);
    }
}