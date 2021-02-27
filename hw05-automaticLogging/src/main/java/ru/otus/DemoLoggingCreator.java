package ru.otus;

import ru.otus.demo.Demo;
import ru.otus.demo.DemoImpl;
import ru.otus.logging.LoggingInvocationHandler;

import java.lang.reflect.Proxy;

public class DemoLoggingCreator {

    public DemoLoggingCreator() {
    }

    public static Demo initLogger() {
        LoggingInvocationHandler loggingInvocationHandler = new LoggingInvocationHandler(new DemoImpl());
        return (Demo) Proxy.newProxyInstance(DemoLoggingCreator.class.getClassLoader(), new Class<?>[]{Demo.class}, loggingInvocationHandler);
    }
}
