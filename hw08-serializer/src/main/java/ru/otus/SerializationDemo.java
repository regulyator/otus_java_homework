package ru.otus;

import com.google.gson.Gson;
import ru.otus.serializer.MyGson;
import ru.otus.serializer.factorys.MyGsonFactory;

import java.util.Arrays;
import java.util.Collection;

public class SerializationDemo {

    public static void main(String[] args) {
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
        System.out.println(json);

        Gson gson = new Gson();
        SampleObject sampleObjectFrom = gson.fromJson(json, SampleObject.class);
        System.out.println(sampleObjectFrom);
    }
}
