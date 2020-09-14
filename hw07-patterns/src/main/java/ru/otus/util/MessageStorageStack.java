package ru.otus.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * история на стаке
 */
public class MessageStorageStack implements MessageStorage {

    private final Stack<MessageSnapshot> messageSnapshots = new Stack<>();

    /**
     * сохраняем в стак
     *
     * @param messageSnapshot шаг истории
     */
    @Override
    public void saveSnapshot(MessageSnapshot messageSnapshot) {
        messageSnapshots.push(messageSnapshot);
    }

    /**
     * берем из стака
     *
     * @return последний сохраненный шаг
     * @throws EmptyStackException если пусто
     */
    @Override
    public MessageSnapshot getLastSavedSnapshot() {
        return messageSnapshots.peek();
    }

    /**
     * @return все шаги в виде листа
     */
    @Override
    public Collection<MessageSnapshot> getAllSnapshots() {
        return new ArrayList<>(messageSnapshots);
    }
}
