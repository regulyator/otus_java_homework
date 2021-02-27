package ru.otus.homework;


import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumbersSequenceHomeWork {

    public static void main(String[] args) throws InterruptedException {
        NumberSequence<Integer> numberSequence = new NumberSequence<>();


        numberSequence.goSequence(Stream.concat(IntStream.rangeClosed(1, 10).boxed(),
                IntStream.rangeClosed(1, 9).boxed().sorted(Collections.reverseOrder()))
                .collect(Collectors.toList()));

    }
}
