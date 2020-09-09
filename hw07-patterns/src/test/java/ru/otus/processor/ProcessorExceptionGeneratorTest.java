package ru.otus.processor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessorExceptionGeneratorTest {
    @Mock
    Supplier<Integer> systemSecondSupplier;

    @Test
    void testGenException() {
        when(systemSecondSupplier.get()).then(invocation -> 2);
        Processor processor = new ProcessorExceptionGenerator(systemSecondSupplier);
        assertThrows(RuntimeException.class, () -> processor.process(null));
    }

    @Test
    void testNoGenException() {
        when(systemSecondSupplier.get()).then(invocation -> 3);
        Processor processor = new ProcessorExceptionGenerator(systemSecondSupplier);
        assertDoesNotThrow(() -> processor.process(null));

    }
}