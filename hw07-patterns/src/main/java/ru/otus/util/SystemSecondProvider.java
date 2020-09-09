package ru.otus.util;

import java.time.LocalTime;
import java.util.function.Supplier;

public class SystemSecondProvider implements Supplier<Integer> {
    @Override
    public Integer get() {
        return LocalTime.now().getSecond();
    }
}
