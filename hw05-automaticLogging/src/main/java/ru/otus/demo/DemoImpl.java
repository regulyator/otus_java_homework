package ru.otus.demo;

import ru.otus.logging.Log;

public class DemoImpl implements Demo {

    @Log
    @Override
    public void calculation(int param1) {
        System.out.println("Calculation 1 " + param1);
    }

    @Log
    @Override
    public void calculation(int param1, int param2) {
        System.out.println("Calculation 2 " + param1 + " " + param2);
    }

    @Log
    @Override
    public void calculation(int param1, int param2, String param3) {
        System.out.println("Calculation 3 " + param1 + " " + param2 + " " + param3);
    }

    @Override
    public void calculation(int param1, int param2, String param3, String param4) {
        System.out.println("Calculation 4 " + param1 + " " + param2 + " " + param3 + " " + param4);
    }
}
