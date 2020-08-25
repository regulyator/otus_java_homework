package ru.otus.homework.agent;

import ru.otus.homework.agent.transform.TestAnnotationTransformer;
import ru.otus.homework.agent.util.LoggedClassFounder;

import java.lang.instrument.Instrumentation;

public class AOPAgentApp {

    public static void premain(String args, Instrumentation instrumentation) {
        instrumentation.addTransformer(new TestAnnotationTransformer(LoggedClassFounder.foundClassesForLogging(instrumentation)));
    }
}
