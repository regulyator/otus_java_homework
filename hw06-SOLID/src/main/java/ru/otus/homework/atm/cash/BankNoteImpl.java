package ru.otus.homework.atm.cash;

import ru.otus.homework.atm.cash.banknotemeta.BanknotesNominalEnum;

import java.util.Objects;

public class BankNoteImpl implements BankNote {

    private final BanknotesNominalEnum banknoteNominal;

    public BankNoteImpl(BanknotesNominalEnum banknoteNominal) {
        if (Objects.isNull(banknoteNominal))
            throw new IllegalArgumentException("banknoteNominal is null");
        this.banknoteNominal = banknoteNominal;
    }

    @Override
    public Integer getBanknoteNominal() {
        return banknoteNominal.getValue().value();
    }
}
