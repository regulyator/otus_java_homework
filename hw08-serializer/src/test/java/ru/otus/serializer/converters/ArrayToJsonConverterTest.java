package ru.otus.serializer.converters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.serializer.factorys.TypeConverterProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class ArrayToJsonConverterTest {
    @Mock
    TypeConverterProvider typeConverterProvider;

    @Test
    void arrayToJson() {
        lenient().when(typeConverterProvider.getConverter(Float.class)).then(invocation -> (ToJsonConverter) object -> object.getClass().equals(Character.class) ?
                String.format("%s%s%s", "\"", object.toString(), "\"") : object.toString());
        lenient().when(typeConverterProvider.getConverter(String.class)).then(invocation -> (ToJsonConverter) object ->
                String.format("%s%s%s", "\"", object.toString(), "\""));
        lenient().when(typeConverterProvider.getConverter(Character.class)).then(invocation -> (ToJsonConverter) object -> object.getClass().equals(Character.class) ?
                String.format("%s%s%s", "\"", object.toString(), "\"") : object.toString());

        char[] chars = {'c', 'h', 'a', 'r'};
        float[] floats = {1.2f, 1.3f, -2.5f};
        String[] strings = {"string", "string1", "string2", "string3"};

        ToJsonConverter arrayToJsonConverter = new ArrayToJsonConverter(typeConverterProvider);

        String charArray = arrayToJsonConverter.toJson(chars);
        String floatArray = arrayToJsonConverter.toJson(floats);
        String stringArray = arrayToJsonConverter.toJson(strings);

        assertEquals("[\"c\",\"h\",\"a\",\"r\"]", charArray);
        assertEquals("[1.2,1.3,-2.5]", floatArray);
        assertEquals("[\"string\",\"string1\",\"string2\",\"string3\"]", stringArray);
    }
}