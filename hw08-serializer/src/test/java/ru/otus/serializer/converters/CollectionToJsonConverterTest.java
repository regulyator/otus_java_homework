package ru.otus.serializer.converters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.serializer.factorys.TypeConverterProvider;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class CollectionToJsonConverterTest {
    @Mock
    TypeConverterProvider typeConverterProvider;

    @Test
    void collectionToJson() {
        lenient().when(typeConverterProvider.getConverter(Float.class)).then(invocation -> (ToJsonConverter) object -> object.getClass().equals(Character.class) ?
                String.format("%s%s%s", "\"", object.toString(), "\"") : object.toString());
        lenient().when(typeConverterProvider.getConverter(String.class)).then(invocation -> (ToJsonConverter) object ->
                String.format("%s%s%s", "\"", object.toString(), "\""));
        lenient().when(typeConverterProvider.getConverter(Character.class)).then(invocation -> (ToJsonConverter) object -> object.getClass().equals(Character.class) ?
                String.format("%s%s%s", "\"", object.toString(), "\"") : object.toString());

        Collection<Character> chars = Arrays.asList('c', 'h', 'a', 'r');
        Collection<Float> floats = Arrays.asList(1.2f, 1.3f, -2.5f);
        Collection<String> strings = Arrays.asList("string", "string1", "string2", "string3");

        ToJsonConverter collectionToJsonConverter = new CollectionToJsonConverter(typeConverterProvider);

        String charArray = collectionToJsonConverter.toJson(chars);
        String floatArray = collectionToJsonConverter.toJson(floats);
        String stringArray = collectionToJsonConverter.toJson(strings);

        assertEquals("[\"c\",\"h\",\"a\",\"r\"]", charArray);
        assertEquals("[1.2,1.3,-2.5]", floatArray);
        assertEquals("[\"string\",\"string1\",\"string2\",\"string3\"]", stringArray);
    }
}