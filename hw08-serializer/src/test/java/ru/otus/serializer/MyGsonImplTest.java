package ru.otus.serializer;

import org.junit.jupiter.api.Test;
import ru.otus.SampleObject;
import ru.otus.serializer.factorys.MyGsonFactory;

import java.util.Arrays;
import java.util.Collection;

class MyGsonImplTest {

    @Test
    void toJson() {
        char[] chars = {'c', 'h', 'a', 'r'};
        Collection<String> strings = Arrays.asList("txt1", "txt2", "txt3");

        SampleObject sampleObject1 = new SampleObject(10,
                (byte) 2,
                'c',
                false,
                chars,
                true,
                20,
                "string",
                strings, null);

        SampleObject sampleObject = new SampleObject(10,
                (byte) 2,
                'c',
                false,
                chars,
                true,
                20,
                "string",
                strings, sampleObject1);
        MyGson myGson = MyGsonFactory.getDefaultMyGsonConverter();

        System.out.println(myGson.toJson(sampleObject));
    }
}