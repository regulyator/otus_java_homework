package ru.otus.homework.diytest;

import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;

public class DIYTest {

    @Before
    public void doBefore(){

    }

    @Test
    public void doTest() {
        System.out.println("diytest");
    }


    @After
    public void doAfter(){

    }
}
