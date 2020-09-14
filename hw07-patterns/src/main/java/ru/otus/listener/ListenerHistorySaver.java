package ru.otus.listener;

import ru.otus.Message;
import ru.otus.util.MessageSnapshot;
import ru.otus.util.MessageStorage;

public class ListenerHistorySaver implements Listener {

    private final MessageStorage messageStorage;

    public ListenerHistorySaver(MessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        messageStorage.saveSnapshot(new MessageSnapshot(oldMsg, newMsg));
    }
}
