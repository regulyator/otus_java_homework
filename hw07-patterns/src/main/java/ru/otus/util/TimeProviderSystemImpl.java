package ru.otus.util;

import java.time.LocalTime;

public class TimeProviderSystemImpl implements TimeProvider {
    @Override
    public Integer getSystemSeconds() {
        return LocalTime.now().getSecond();
    }
}
