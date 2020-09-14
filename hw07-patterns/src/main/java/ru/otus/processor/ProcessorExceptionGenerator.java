package ru.otus.processor;

import ru.otus.Message;
import ru.otus.util.TimeProvider;

public class ProcessorExceptionGenerator implements Processor {

    private final TimeProvider timeProvider;

    public ProcessorExceptionGenerator(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    @Override
    public Message process(Message message) {
        if (timeProvider.getSystemSeconds() % 2 == 0) {
            throw new RuntimeException("Second is even. Time to die:(");
        }
        return message;
    }
}
