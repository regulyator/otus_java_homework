package ru.otus.homework.agent.transform;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import ru.otus.homework.agent.asmvisitors.LogClassVisitor;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.List;
import java.util.stream.Stream;

public class TestAnnotationTransformer implements ClassFileTransformer {

    private final List<String> loggedClasses;

    public TestAnnotationTransformer(List<String> loggedClasses) {
        this.loggedClasses = loggedClasses;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if(className.contains("SomeBoringClass")){
            System.out.println("sdfsdfsdf");
        }
        LogClassVisitor cv = new LogClassVisitor();
        ClassReader cr = null;
        try {
            cr = new ClassReader(className);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cr.accept(cv, 0);




        /*Stream.of(classBeingRedefined.getDeclaredMethods()).forEach(method -> {
            *//*if (LoggedClassFounder.isLoggedMethod(method)) {
                System.out.println(className + "::" + classBeingRedefined.getName() + "::" + method);
            }*//*
        });*/

        return classfileBuffer;
    }


}
