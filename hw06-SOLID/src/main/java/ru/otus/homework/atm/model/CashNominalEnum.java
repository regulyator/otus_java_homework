package ru.otus.homework.atm.model;

import java.util.HashMap;
import java.util.Map;

public enum CashNominalEnum {
    CASH_NOMINAL_5000("5000"), CASH_NOMINAL_2000("2000"),
    CASH_NOMINAL_1000("1000"), CASH_NOMINAL_200("200"),
    CASH_NOMINAL_500("500"), CASH_NOMINAL_100("100"), CASH_NOMINAL_50("50");

    private String value;

    private static final Map<String, CashNominalEnum> reverseMapNominal = new HashMap<>();

    static {
        for (CashNominalEnum d : CashNominalEnum.values()) {
            reverseMapNominal.put(d.getValue(), d);
        }
    }

    public static Map<String, CashNominalEnum> getReverseMapNominal() {
        return reverseMapNominal;
    }

    CashNominalEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
