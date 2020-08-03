package ru.otus.homework.testworker.testproccesor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.homework.testworker.util.TestBatch;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class DefaultTestProcessorImplTest {


    private TestProcessor testProcessor;
    private List<String> testForTestClassNames;
    private List<String> invalidTestClassNames;

    @BeforeEach
    void setUp() {
        testProcessor = new DefaultTestProcessorImpl("ru.otus.homework.testfortest");

        testForTestClassNames = List.of("ru.otus.homework.testfortest.DIYTest1",
                "ru.otus.homework.testfortest.DIYTest2",
                "ru.otus.homework.testfortest.DIYTest4");

        invalidTestClassNames = List.of("ru.otus.homework.testfortest.DIYTest3",
                "ru.otus.homework.testfortest.DIYTest5",
                "ru.otus.homework.testfortest.DIYTest6");
    }

    @Test
    void checkGetAllTestClasses() {
        Map<String, Class> tests = testProcessor.getAllTestClasses();

        assertEquals(testForTestClassNames.size(), tests.size());

    }

    @Test
    void checkContentOfTestClasses() {
        Map<String, Class> tests = testProcessor.getAllTestClasses();

        testForTestClassNames.forEach(s -> {
            assertTrue(tests.containsKey(s));
            assertTrue(Objects.nonNull(tests.get(s)));
        });

        invalidTestClassNames.forEach(s -> {
            assertFalse(tests.containsKey(s));
            assertFalse(Objects.nonNull(tests.get(s)));
        });

    }

    @Test
    void checkGenerateTestBatches() {
        List<TestBatch> testBatches = testProcessor.generateTestBatches();

        assertEquals(testForTestClassNames.size(), testBatches.size());

        testBatches.forEach(testBatch -> assertTrue(Objects.nonNull(testBatch.getTestMethods())
                && testBatch.getTestMethods().size() > 0));
    }


    @Test
    void checkGenerateTestBatchesContent() {
        List<TestBatch> testBatches = testProcessor.generateTestBatches();

        assertEquals(testForTestClassNames.size(), testBatches.size());

        testBatches.forEach(testBatch -> {
            testBatch.getTestMethods().forEach(method -> {
                assertEquals(testBatch.getTestClass(), method.getDeclaringClass());
                assertFalse(invalidTestClassNames.contains(method.getDeclaringClass().getCanonicalName()));
            });
        });
    }
}