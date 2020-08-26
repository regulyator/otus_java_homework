package ru.otus.homework.agent.asmvisitors;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;

public class LogAnnotationVisitor extends AnnotationVisitor {

    public LogAnnotationVisitor() {
        super(Opcodes.ASM8);
    }

    @Override
    public void visit(String name, Object value) {
        System.out.println(name);
        super.visit(name, value);
    }


}
