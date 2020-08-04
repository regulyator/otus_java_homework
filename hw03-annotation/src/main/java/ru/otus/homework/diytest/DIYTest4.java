package ru.otus.homework.diytest;

import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;
import ru.otus.homework.exeptions.TestFailException;

/**
 * Класс "теста" для примера
 * фэйлится т.к. при попытке инстанцировать через дефолтный конструктор - вываливается
 */
public class DIYTest4 {

    public DIYTest4() {
        throw new NullPointerException();
    }

    @Before
    public void doBefore1() {
        System.out.println("before41");
    }


    @Before
    public void doBefore2() {
        System.out.println("before41");
    }

    @Test
    public void doTest() {
        System.out.println("diytest41");
        throw new TestFailException("TEST FAIL");
    }

    @Test
    public void doTest2() {
        System.out.println("diytest42");
    }

    @Test
    public void doTest3() {
        System.out.println("diytest43");
    }

    @After
    public void doAfter1() {
        System.out.println("after41");
    }

    @After
    public void doAfter2() {
        System.out.println("after41");
    }
}
