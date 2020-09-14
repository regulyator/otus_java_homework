package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.ListenerHistorySaver;
import ru.otus.processor.ProcessorConcatFields;
import ru.otus.processor.ProcessorExceptionGenerator;
import ru.otus.processor.ProcessorFieldSwapper;
import ru.otus.processor.ProcessorUpperField10;
import ru.otus.util.MessageStorageStack;
import ru.otus.util.TimeProviderSystemImpl;

import java.time.LocalTime;
import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13
       2. Сделать процессор, который поменяет местами значения field11 и field13
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
       4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)

       по аналогии с Demo.class
       из элеменов "to do" создать new ComplexProcessor и обработать сообщение
     */

    public static void main(String[] args) {

        var processors = List.of(new ProcessorFieldSwapper(),
                new ProcessorExceptionGenerator(new TimeProviderSystemImpl()),
                new ProcessorConcatFields(),
                new ProcessorUpperField10());

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
            System.out.println("Handle error!" + ex.getMessage());
        });


        var messageStorageStack = new MessageStorageStack();
        var listenerHistorySaver = new ListenerHistorySaver(messageStorageStack);
        complexProcessor.addListener(listenerHistorySaver);

        var message = new Message.Builder()
                .field1("field1")
                .field2("field2")
                .field3("field3")
                .field6("field6")
                .field10("field10")
                .field11("field11")
                .field12("field12")
                .field13("field13")
                .build();

        var result = complexProcessor.handle(message);
        System.out.println("Final result:" + result);

        complexProcessor.removeListener(listenerHistorySaver);

        messageStorageStack.getAllSnapshots().forEach(messageSnapshot -> {
            System.out.println(String.format("oldMsg:%s, newMsg:%s", messageSnapshot.getOldMessage(), messageSnapshot.getNewMessage()));
        });
    }
}
