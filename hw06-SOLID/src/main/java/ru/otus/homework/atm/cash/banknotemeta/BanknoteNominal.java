package ru.otus.homework.atm.cash.banknotemeta;

public class BanknoteNominal {

    private final Integer nominal;

    public BanknoteNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public Integer value() {
        return this.nominal;
    }
}
