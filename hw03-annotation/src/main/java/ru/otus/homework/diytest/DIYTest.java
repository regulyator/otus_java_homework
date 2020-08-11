package ru.otus.homework.diytest;

import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;

/**
 * Класс "теста" для примера
 * полностью рабочий
 */
public class DIYTest {

    @Before
    public void doBefore() {
        System.out.println("before");
    }

    @Test
    public void doTest() {
        System.out.println("diytest");
    }


    @After
    public void doAfter() {
        System.out.println("after");
    }
}
