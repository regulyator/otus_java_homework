package ru.otus.homework.agent.asmvisitors;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LogMethodVisitor extends MethodVisitor {
    public LogMethodVisitor() {
        super(Opcodes.ASM8);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return new LogAnnotationVisitor();
    }

    @Override
    public void visitCode() {
        super.visitCode();
    }
}
