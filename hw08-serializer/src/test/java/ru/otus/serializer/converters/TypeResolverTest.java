package ru.otus.serializer.converters;

import org.junit.jupiter.api.Test;
import ru.otus.SampleObject;
import ru.otus.serializer.meta.Type;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeResolverTest {

    @Test
    void resolveType() {
        TypeResolver typeResolver = new TypeResolverImpl();

        String string = "string";
        char[] chars = {'c', 'h', 'a', 'r'};
        Collection<?> characterList = Arrays.asList('c', 'h', 'a', 'r');
        SampleObject sampleObject = new SampleObject(10,
                (byte) 2,
                'c',
                false,
                chars,
                true,
                20,
                "string",
                null, null);

        assertEquals(Type.ARRAY, typeResolver.resolveType(chars.getClass()));
        assertEquals(Type.COLLECTION, typeResolver.resolveType(characterList.getClass()));
        assertEquals(Type.PRIMITIVE, typeResolver.resolveType(byte.class));
        assertEquals(Type.PRIMITIVE, typeResolver.resolveType(short.class));
        assertEquals(Type.STRING, typeResolver.resolveType(string.getClass()));
        assertEquals(Type.OBJECT, typeResolver.resolveType(sampleObject.getClass()));
    }

}