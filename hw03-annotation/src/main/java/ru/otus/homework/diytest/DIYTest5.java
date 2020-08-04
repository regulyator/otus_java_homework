package ru.otus.homework.diytest;

import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;
import ru.otus.homework.exeptions.TestFailException;

/**
 * Класс "теста" для примера
 * фэйлится т.к. вываливается метод сетапа doAfter2
 */
public class DIYTest5 {


    @Before
    public void doBefore1() {
        System.out.println("before51");
    }


    @Before
    public void doBefore2() {
        System.out.println("before51");
    }

    @Test
    public void doTest() {
        System.out.println("diytest51");
        throw new TestFailException("TEST FAIL");
    }

    @Test
    public void doTest2() {
        System.out.println("diytest52");
    }

    @Test
    public void doTest3() {
        System.out.println("diytest53");
    }

    @After
    public void doAfter1() {
        System.out.println("after51");
    }

    @After
    public void doAfter2() {
        System.out.println("after52");
        throw new NullPointerException();
    }
}
