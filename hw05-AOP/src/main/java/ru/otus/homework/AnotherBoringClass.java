package ru.otus.homework;

import ru.otus.homework.agent.annotation.Log;

public class AnotherBoringClass {

    @Log
    public void saySomethingNotSoBoring(String whatToSay) {
        System.out.println(whatToSay);
    }

    public void sayNothing(String whatToSay, int intVar, Long longVar) {
        System.out.println(whatToSay + intVar + longVar);
    }
}
