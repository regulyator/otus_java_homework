package ru.otus;

import com.google.gson.Gson;

public class HomeWork {

    public static void main(String[] args) {
        Gson gson = new Gson();
        SampleObject sampleObject = new SampleObject(10, 20, "text", someCollection);
        String json = gson.toJson(sampleObject);
        System.out.println(json);
    }
}
