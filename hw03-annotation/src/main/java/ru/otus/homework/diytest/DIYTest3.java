package ru.otus.homework.diytest;

import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;

/**
 * Класс "теста" для примера
 * фэйлится т.к. вываливается метод сетапа - doBefore1
 */
public class DIYTest3 {


    @Before
    public void doBefore1() {
        throw new NullPointerException();
    }


    @Before
    public void doBefore2() {
        System.out.println("before31");
    }

    @Test
    public void doTest(String params) {
        System.out.println("diytest31");
    }

    @Test
    public void doTest2() {
        System.out.println("diytest32");
    }

    @Test
    public void doTest3() {
        System.out.println("diytest33");
    }

    @After
    public void doAfter1() {
        System.out.println("after31");
    }

    @After
    public void doAfter2() {
        System.out.println("after32");
    }
}
