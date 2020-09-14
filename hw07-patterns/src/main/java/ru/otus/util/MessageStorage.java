package ru.otus.util;

import java.util.Collection;

public interface MessageStorage {

    void saveSnapshot(MessageSnapshot messageSnapshot);

    MessageSnapshot getLastSavedSnapshot();

    Collection<MessageSnapshot> getAllSnapshots();
}
