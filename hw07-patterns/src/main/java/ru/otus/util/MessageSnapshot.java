package ru.otus.util;

import ru.otus.Message;

public class MessageSnapshot {
    private final Message oldMessage;
    private final Message newMessage;


    public MessageSnapshot(Message oldMessage, Message newMessage) {
        this.oldMessage = oldMessage;
        this.newMessage = newMessage;
    }

    public Message getOldMessage() {
        return oldMessage;
    }

    public Message getNewMessage() {
        return newMessage;
    }
}
