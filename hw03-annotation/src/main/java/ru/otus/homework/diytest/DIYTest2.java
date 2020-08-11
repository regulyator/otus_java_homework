package ru.otus.homework.diytest;

import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;
import ru.otus.homework.exeptions.TestFailException;

/**
 * Класс "теста" для примера
 * выполняются все методы @Before и @After
 * тест - doTest фэйлится
 * тесты - doTest2, doTest3 - успешные
 */
public class DIYTest2 {


    @Before
    public void doBefore1() {
        System.out.println("before21");
    }


    @Before
    public void doBefore2() {
        System.out.println("before21");
    }

    @Test
    public void doTest() {
        System.out.println("diytest21");
        throw new TestFailException("TEST FAIL");
    }

    @Test
    public void doTest2() {
        System.out.println("diytest22");
    }

    @Test
    public void doTest3() {
        System.out.println("diytest23");
    }

    @After
    public void doAfter1() {
        System.out.println("after21");
    }

    @After
    public void doAfter2() {
        System.out.println("after21");
    }
}
