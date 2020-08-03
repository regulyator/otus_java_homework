package ru.otus.homework;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import ru.otus.homework.annotations.Test;
import ru.otus.homework.testworker.testproccesor.DefaultTestProcessorImpl;
import ru.otus.homework.testworker.testrunner.TestRunner;
import ru.otus.homework.testworker.testrunner.TestRunnerImpl;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DIYTestFrameworkApp {

    public static void main(String[] args) {
        TestRunner testRunner = new TestRunnerImpl(new DefaultTestProcessorImpl());
        testRunner.prepare();
        testRunner.runTests();
    }
}
