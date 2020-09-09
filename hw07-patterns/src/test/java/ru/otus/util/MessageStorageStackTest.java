package ru.otus.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.Message;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessageStorageStackTest {
    @Mock
    Message oldMessage;
    @Mock
    Message newMessage;

    @Test
    void saveSnapshot() {
        MessageSnapshot messageSnapshot = new MessageSnapshot(oldMessage, newMessage);
        MessageStorage messageStorage = new MessageStorageStack();
        messageStorage.saveSnapshot(messageSnapshot);
        assertEquals(messageSnapshot, messageStorage.getLastSavedSnapshot());
    }

    @Test
    void getLastSavedSnapshot() {
        MessageSnapshot messageSnapshot = new MessageSnapshot(oldMessage, newMessage);
        MessageStorage messageStorage = new MessageStorageStack();
        messageStorage.saveSnapshot(messageSnapshot);
        messageStorage.saveSnapshot(messageSnapshot);
        messageStorage.getLastSavedSnapshot();
        assertDoesNotThrow(messageStorage::getLastSavedSnapshot);
        assertThrows(EmptyStackException.class, messageStorage::getLastSavedSnapshot);
    }

    @Test
    void getAllSnapshots() {
        MessageSnapshot messageSnapshot = new MessageSnapshot(oldMessage, newMessage);
        List<MessageSnapshot> messageSnapshotList = List.of(messageSnapshot, messageSnapshot);
        MessageStorage messageStorage = new MessageStorageStack();
        messageStorage.saveSnapshot(messageSnapshot);
        messageStorage.saveSnapshot(messageSnapshot);

        Collection<MessageSnapshot> savedList = messageStorage.getAllSnapshots();

        assertEquals(savedList.size(), 2);
        assertEquals(messageSnapshotList, savedList);
        assertDoesNotThrow(messageStorage::getLastSavedSnapshot);
        assertDoesNotThrow(messageStorage::getLastSavedSnapshot);

        savedList = messageStorage.getAllSnapshots();

        assertEquals(savedList.size(), 0);

    }
}