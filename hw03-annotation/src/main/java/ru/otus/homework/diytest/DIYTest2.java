package ru.otus.homework.diytest;

import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;

public class DIYTest2 {


    @Before
    public void doBefore1(){
        System.out.println("before21");
    }


    @Before
    public void doBefore2(){
        System.out.println("before21");
    }

    @Test
    public void doTest() {
        System.out.println("diytest21");
    }

    @Test
    public void doTest2() {
        System.out.println("diytest22");
    }

    @After
    public void doAfter1(){
        System.out.println("after21");
    }

    @After
    public void doAfter2(){
        System.out.println("after21");
    }
}
