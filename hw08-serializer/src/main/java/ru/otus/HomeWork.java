package ru.otus;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Collection;

public class HomeWork {

    public static void main(String[] args) {
        char[] chars = {'c', 'h', 'a', 'r'};
        Collection<String> strings = Arrays.asList("txt1", "txt2", "txt3");

        Gson gson = new Gson();
        SampleObject sampleObject = new SampleObject(10,
                (byte) 2,
                'c',
                false,
                chars,
                true,
                20,
                "string",
                strings);
        String json = gson.toJson(sampleObject);
        System.out.println(json);
    }
}
