package ru.otus.homework.util;

import ru.otus.homework.annotation.Log;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LoggedClassFounder {

    public static List<Class<?>> getAllLoggedClasses(Instrumentation instrumentation) {
        return Arrays.stream(instrumentation.getAllLoadedClasses())
                .filter(clazz -> Arrays.stream(clazz.getMethods())
                        .anyMatch(method -> method.isAnnotationPresent(Log.class)))
                .collect(Collectors.toList());
    };
}
