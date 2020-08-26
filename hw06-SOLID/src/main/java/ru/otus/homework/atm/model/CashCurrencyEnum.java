package ru.otus.homework.atm.model;

import java.util.HashMap;
import java.util.Map;

public enum CashCurrencyEnum {
    RU("RU"), US_DOLLAR("US_DOLLAR");

    private String value;

    CashCurrencyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
