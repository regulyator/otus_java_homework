package ru.otus.homework.testfortest;

import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;
import ru.otus.homework.exeptions.TestFailException;

public class DIYTest2 {

    public DIYTest2() {
        throw new NullPointerException();
    }

    @Before
    public void doBefore1() {

    }


    @Before
    public void doBefore2() {

    }

    @Test
    public void doTest1() {

    }

    @Test
    public void doTest2() {
        throw new TestFailException("TEST FAIL");
    }

    @After
    public void doAfter1() {

    }

    @After
    public void doAfter2() {

    }
}
