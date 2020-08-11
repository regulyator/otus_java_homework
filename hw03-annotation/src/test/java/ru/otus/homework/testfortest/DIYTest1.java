package ru.otus.homework.testfortest;

import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;

public class DIYTest1 {

    @Before
    public void doBefore1() {
        throw new NullPointerException();
    }

    @Test
    public void doTest1() {

    }

    @After
    public void doAfter1() {
        this.someUnAnnotatedMethod();
    }

    public void someUnAnnotatedMethod() {

    }
}
