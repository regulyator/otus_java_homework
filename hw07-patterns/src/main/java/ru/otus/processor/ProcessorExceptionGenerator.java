package ru.otus.processor;

import ru.otus.Message;

import java.util.function.Supplier;

public class ProcessorExceptionGenerator implements Processor {

    private final Supplier<Integer> systemSecondSupplier;

    public ProcessorExceptionGenerator(Supplier<Integer> systemSecondSupplier) {
        this.systemSecondSupplier = systemSecondSupplier;
    }

    @Override
    public Message process(Message message) {
        if (systemSecondSupplier.get() % 2 == 0) {
            throw new RuntimeException("Second is even. Time to die:(");
        }
        return message;
    }
}
