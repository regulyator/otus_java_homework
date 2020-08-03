package ru.otus.homework.proccessors.testproccesor;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;
import ru.otus.homework.proccessors.util.TestBatch;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class DefaultTestProcessorImpl implements TestProcessor {

    private static final String DEFAULT_ROOT_TEST_PACKAGE = "ru.otus.homework.diytest";
    private final Map<String, Class> testClasses = new HashMap<>();
    private final List<TestBatch> testBatches = new ArrayList<>();
    private final String rootTestPackage;
    private final Reflections reflectionsPackageScanner;

    public DefaultTestProcessorImpl() {
        this(DEFAULT_ROOT_TEST_PACKAGE);
    }

    public DefaultTestProcessorImpl(String rootTestPackage) {
        this.rootTestPackage = rootTestPackage;
        this.reflectionsPackageScanner = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(this.rootTestPackage))
                .setScanners(new MethodAnnotationsScanner()));
    }

    @Override
    public Map<String, Class> getAllTestClasses() {
        this.testClasses.clear();

        this.reflectionsPackageScanner
                .getMethodsAnnotatedWith(Test.class)
                .forEach(method -> {
                    if (method.isAnnotationPresent(Test.class)
                            && !testClasses.containsKey(method.getDeclaringClass().getCanonicalName())) {
                        testClasses.put(method.getDeclaringClass().getCanonicalName(),
                                method.getDeclaringClass());
                    }
                });

        return this.testClasses;
    }

    @Override
    public List<TestBatch> generateTestBatches() {
        this.testBatches.clear();

        this.getAllTestClasses().forEach((s, aClass) -> {
            if (Objects.nonNull(aClass)) {
                Method[] clazzMethods = aClass.getDeclaredMethods();
                if (clazzMethods.length > 0
                        && Arrays.stream(clazzMethods).anyMatch(method -> method.isAnnotationPresent(Test.class))) {
                    generateTestBatch(clazzMethods);

                }
            }
        });

        return this.testBatches;
    }

    private void generateTestBatch(Method[] clazzMethods) {
        TestBatch testBatch = new TestBatch();

        testBatch.setBeforeMethods(
                Arrays.stream(clazzMethods)
                        .filter(method -> method.isAnnotationPresent(Before.class))
                        .collect(Collectors.toList()));

        testBatch.setAfterMethods(
                Arrays.stream(clazzMethods)
                        .filter(method -> method.isAnnotationPresent(After.class))
                        .collect(Collectors.toList()));

        testBatch.setTestMethods(
                Arrays.stream(clazzMethods)
                        .filter(method -> method.isAnnotationPresent(Test.class))
                        .collect(Collectors.toList()));

        this.testBatches.add(testBatch);
    }

    public Map<String, Class> getTestClasses() {
        return testClasses;
    }

    public List<TestBatch> getTestBatches() {
        return testBatches;
    }
}
