package ru.otus;

import ru.otus.demo.Demo;

public class AutomaticLoggingDemo {

    public static void main(String[] args) {
        Demo demo = DemoLoggingCreator.initLogger();
        demo.calculation(1);
        demo.calculation(1, 2);
        demo.calculation(1, 2, "param3");
        demo.calculation(1, 2, "param3", "param4");
    }
}
