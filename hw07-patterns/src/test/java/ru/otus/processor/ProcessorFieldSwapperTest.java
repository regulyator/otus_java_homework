package ru.otus.processor;

import org.junit.jupiter.api.Test;
import ru.otus.Message;

import static org.assertj.core.api.Assertions.assertThat;

class ProcessorFieldSwapperTest {

    @Test
    void testFieldSwap() {
        Message message = new Message.Builder()
                .field11("field11")
                .field13("field13")
                .build();

        Processor swapper = new ProcessorFieldSwapper();

        Message swappedMessage = swapper.process(message);

        assertThat(swappedMessage.getField11()).isEqualTo(message.getField13());
        assertThat(swappedMessage.getField13()).isEqualTo(message.getField11());

    }
}