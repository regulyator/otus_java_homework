package ru.otus.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Benchmark {

    private int addBatchSize = 20;
    private int removeBatchSize = 10;

    public Benchmark(int addBatchSize, int removeBatchSize) {
        this.addBatchSize = addBatchSize;
        this.removeBatchSize = removeBatchSize;
    }

    void run() throws InterruptedException {
        List<String> strings = new ArrayList<>();
        while (true) {
            strings.addAll(IntStream
                    .range(0, addBatchSize)
                    .mapToObj(value -> "String" + value)
                    .collect(Collectors.toList()));
            for (int i = 0; i < removeBatchSize; i++) {
                strings.remove(i);
            }
        }
    }
}
