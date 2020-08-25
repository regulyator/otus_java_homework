package ru.otus.homework.agent.util;

import ru.otus.homework.agent.annotation.Log;

import java.lang.annotation.Annotation;
import java.lang.instrument.Instrumentation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LoggedClassFounder {

    private final static Class<? extends Annotation> LOG_ANNOTATION = Log.class;

    public static List<String> foundClassesForLogging(Instrumentation instrumentation) {
        return Arrays.stream(instrumentation.getAllLoadedClasses())
                .filter(clazz -> Arrays.stream(clazz.getDeclaredMethods())
                        .anyMatch(method -> method.isAnnotationPresent(LOG_ANNOTATION)))
                .map(Class::getCanonicalName)
                .collect(Collectors.toList());
    }
}
