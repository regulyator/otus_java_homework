package ru.otus.serializer.converters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimitiveToJsonConverterTest {

    @Test
    void primitiveToJson() {
        ToJsonConverter toJsonConverter = new PrimitiveToJsonConverter();

        byte b = 2;
        short s = 12;
        int i = 21;
        long l = 30L;
        float f = 1.2f;
        double d = 21.6d;
        char c = 'c';
        boolean b1 = false;

        assertEquals("2", toJsonConverter.toJson(b));
        assertEquals("12", toJsonConverter.toJson(s));
        assertEquals("21", toJsonConverter.toJson(i));
        assertEquals("30", toJsonConverter.toJson(l));
        assertEquals("1.2", toJsonConverter.toJson(f));
        assertEquals("21.6", toJsonConverter.toJson(d));
        assertEquals("\"c\"", toJsonConverter.toJson(c));
        assertEquals("false", toJsonConverter.toJson(b1));
    }
}