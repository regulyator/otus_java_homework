package ru.otus.homework.agent.asmvisitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class LogClassVisitor extends ClassVisitor {


    public LogClassVisitor() {
        super(Opcodes.ASM8);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return new LogMethodVisitor();
    }


}
