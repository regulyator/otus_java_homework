package ru.otus.serializer;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import ru.otus.SampleObject;
import ru.otus.serializer.factorys.MyGsonFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyGsonImplTest {

    @Test
    void toJson() {
        char[] chars = {'c', 'h', 'a', 'r'};
        Collection<String> strings = Arrays.asList("txt1", "txt2", "txt3");

        SampleObject sampleObjectInner = new SampleObject(10,
                (byte) 2,
                'c',
                false,
                chars,
                true,
                20,
                null,
                null, null);

        SampleObject sampleObject = new SampleObject(10,
                (byte) 2,
                'c',
                false,
                chars,
                true,
                20,
                "string",
                strings, sampleObjectInner);

        MyGson myGson = MyGsonFactory.getDefaultMyGsonConverter();
        String json = myGson.toJson(sampleObject);


        Gson gson = new Gson();
        SampleObject sampleObjectFrom = gson.fromJson(json, SampleObject.class);

        assertEquals(sampleObjectFrom, sampleObject);
    }
}