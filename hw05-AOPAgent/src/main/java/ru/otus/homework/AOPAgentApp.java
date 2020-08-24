package ru.otus.homework;

import ru.otus.homework.annotation.Log;
import ru.otus.homework.transform.TestAnnotationTransformer;
import ru.otus.homework.util.LoggedClassFounder;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AOPAgentApp {

    public static void premain(String arg, Instrumentation instrumentation) {
        instrumentation.addTransformer(new TestAnnotationTransformer(instrumentation, LoggedClassFounder.getAllLoggedClasses(instrumentation) ));
    }

    /*Arrays.stream(instrumentation.getAllLoadedClasses())
            .filter(clazz -> Arrays.stream(clazz.getMethods())
            .anyMatch(method -> method.isAnnotationPresent(Log .class)))
            .collect(Collectors.toList());*/
}
