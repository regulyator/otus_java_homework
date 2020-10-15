package ru.otus.serializer;

import org.junit.jupiter.api.Test;
import ru.otus.SampleObject;

class MyGsonImplTest {

    @Test
    void toJson() {
        SampleObject sampleObject = new SampleObject(10, 20, "text", someCollection);
        MyGson myGson = MyGsonFactory.getDefaultMyGsonConverter();
        System.out.println(myGson.toJson(sampleObject));
    }
}