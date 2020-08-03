package ru.otus.homework;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import ru.otus.homework.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DIYTestFrameworkApp {

    public static void main(String[] args) {
        Map<String, Class> testClasses = new HashMap<>(2);

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("ru.otus.homework.diytest"))
                .setScanners(new MethodAnnotationsScanner()));
        Set<Method> tests = reflections.getMethodsAnnotatedWith(Test.class);

        tests.forEach(method -> {
            if (method.isAnnotationPresent(Test.class)
                    && !testClasses.containsKey(method.getDeclaringClass().getCanonicalName())) {
                testClasses.put(method.getDeclaringClass().getCanonicalName(),
                        method.getDeclaringClass());
            }
        });

        testClasses.forEach((s, aClass) -> {
            System.out.println(s);
            Arrays.stream(aClass.getDeclaredMethods()).forEach(method -> {
                if (method.isAnnotationPresent(Test.class)) {
                    System.out.println(method.getName());
                }
            });
        });
    }
}
