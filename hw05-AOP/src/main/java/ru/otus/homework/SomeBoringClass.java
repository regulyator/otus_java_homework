package ru.otus.homework;

import ru.otus.homework.agent.annotation.Log;

public class SomeBoringClass {

    @Log
    public void saySomething(String whatToSay) {
        System.out.println(whatToSay);
    }

    @Log
    public void saySomethingMoreComplex(String whatToSay, int intVar, Long longVar) {
        System.out.println(whatToSay + intVar + longVar);
    }

    @Log
    public void saySomethingLittleBitMoreComplex(String whatToSay, int intVar, Long longVar, Double... doubleVars) {
        System.out.println(whatToSay + intVar + longVar + doubleVars);
    }
}
