package ru.otus.homework.testfortest;

import ru.otus.homework.annotations.Test;
import ru.otus.homework.exeptions.TestFailException;

public class DIYTest4 {

    @Test
    public void doTest1() {

    }

    @Test
    public void doTest2() {
        throw new TestFailException("TEST FAIL");
    }
}
