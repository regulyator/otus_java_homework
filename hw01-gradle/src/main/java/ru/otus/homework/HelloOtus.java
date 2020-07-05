package ru.otus.homework;

import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import com.google.common.collect.ContiguousSet;

import java.util.Objects;

public class HelloOtus {
    public static void main(String[] args) {

        System.out.println(Joiner
                .on(", ")
                .join(Collections2.filter(ContiguousSet.closed(1, 20), input -> Objects.nonNull(input) && input % 2 != 0)));

    }
}
