package ru.otus.processor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.util.TimeProvider;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessorExceptionGeneratorTest {
    @Mock
    TimeProvider timeProvider;

    @Test
    void testGenException() {
        when(timeProvider.getSystemSeconds()).then(invocation -> 2);
        Processor processor = new ProcessorExceptionGenerator(timeProvider);
        assertThrows(RuntimeException.class, () -> processor.process(null));
    }

    @Test
    void testNoGenException() {
        when(timeProvider.getSystemSeconds()).then(invocation -> 3);
        Processor processor = new ProcessorExceptionGenerator(timeProvider);
        assertDoesNotThrow(() -> processor.process(null));

    }
}