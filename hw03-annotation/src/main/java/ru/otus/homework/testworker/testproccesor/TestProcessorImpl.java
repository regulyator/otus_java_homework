package ru.otus.homework.testworker.testproccesor;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;
import ru.otus.homework.testworker.util.TestBatch;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static ru.otus.homework.testworker.util.reflection.ReflectionUtil.checkForDefaultConstructor;
import static ru.otus.homework.testworker.util.reflection.ReflectionUtil.checkMethodHaveNoParams;

public class TestProcessorImpl implements TestProcessor {

    // пакет для сканирования по умолчанию
    private static final String DEFAULT_ROOT_TEST_PACKAGE = "ru.otus.homework.diytest";
    // тут храним найденные классы
    private final Map<String, Class<?>> testClasses = new HashMap<>();
    // сгенерерованные "пакеты" с тестами
    private List<TestBatch> testBatches;
    private final String rootTestPackage;
    // для сканирования по пакету
    private final Reflections reflectionsPackageScanner;

    public TestProcessorImpl() {
        this(DEFAULT_ROOT_TEST_PACKAGE);
    }

    public TestProcessorImpl(String rootTestPackage) {
        this.rootTestPackage = rootTestPackage;
        // инициализируем сканер по пакету
        this.reflectionsPackageScanner = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(this.rootTestPackage))
                .setScanners(new MethodAnnotationsScanner()));
    }

    /**
     * ищет все классы стестами,
     * добавляются только те где есть дефолтный конструктор
     * и есть хотя бы один метод теста @Test без параметров
     *
     * @return возвращает мапу с canonicalName классов с тестами и сам Class
     */
    @Override
    public Map<String, Class<?>> getAllTestClasses() {
        this.testClasses.clear();

        this.reflectionsPackageScanner
                .getMethodsAnnotatedWith(Test.class)
                .forEach(method -> {
                    if (method.isAnnotationPresent(Test.class)
                            && !testClasses.containsKey(method.getDeclaringClass().getCanonicalName())
                            && checkForDefaultConstructor(method.getDeclaringClass())
                            && checkMethodHaveNoParams(method)) {
                        testClasses.put(method.getDeclaringClass().getCanonicalName(),
                                method.getDeclaringClass());
                    }
                });

        return this.testClasses;
    }

    /**
     * генератор "пакетов" с тестами
     *
     * @return возвращает лист с "пакетами" для тестов
     */
    @Override
    public List<TestBatch> generateTestBatches() {
        this.testBatches = new ArrayList<>();
        if (testClasses.isEmpty()) {
            getAllTestClasses();
        }

        this.getAllTestClasses().forEach((testClassName, testClass) -> {
            if (Objects.nonNull(testClass)) {
                Method[] clazzMethods = testClass.getDeclaredMethods();
                if (clazzMethods.length > 0
                        && Arrays.stream(clazzMethods).anyMatch(method -> method.isAnnotationPresent(Test.class))) {
                    generateTestBatch(clazzMethods, testClass);

                }
            }
        });

        return this.testBatches;
    }

    /**
     * собственно сама генерация "пакетов" для тестов
     * проверяем что требуемые методы не имеют параметров
     *
     * @param clazzMethods методы класса
     * @param testClass    класс тестов
     */
    private void generateTestBatch(Method[] clazzMethods, Class<?> testClass) {
        TestBatch testBatch = new TestBatch(testClass);

        testBatch.setBeforeMethods(
                Arrays.stream(clazzMethods)
                        .filter(method -> method.isAnnotationPresent(Before.class)
                                && checkMethodHaveNoParams(method))
                        .collect(Collectors.toList()));

        testBatch.setAfterMethods(
                Arrays.stream(clazzMethods)
                        .filter(method -> method.isAnnotationPresent(After.class)
                                && checkMethodHaveNoParams(method))
                        .collect(Collectors.toList()));

        testBatch.setTestMethods(
                Arrays.stream(clazzMethods)
                        .filter(method -> method.isAnnotationPresent(Test.class)
                                && checkMethodHaveNoParams(method))
                        .collect(Collectors.toList()));

        this.testBatches.add(testBatch);
    }

    public Map<String, Class<?>> getTestClasses() {
        return testClasses;
    }

    public List<TestBatch> getTestBatches() {
        return testBatches;
    }
}
