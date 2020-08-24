package ru.otus.homework.transform;

import ru.otus.homework.annotation.Log;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestAnnotationTransformer implements ClassFileTransformer {

    private final Instrumentation instrumentation;
    private final List<? extends Class<?>> loggedClasses;

    public TestAnnotationTransformer(Instrumentation instrumentation, List<Class<?>> loggedClasses) {
        this.instrumentation = instrumentation;
        this.loggedClasses = loggedClasses;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {


        return new byte[0];
    }

    private void logAnnotationTransform(List<Method> collect) {

    }


}
