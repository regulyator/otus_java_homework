package ru.otus.homework.atm;

import ru.otus.homework.atm.atmstructure.BankNoteDispenser;
import ru.otus.homework.atm.cash.BankNote;
import ru.otus.homework.atm.exceptions.IncorrectSummException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ATMImpl implements ATM {

    private final BankNoteDispenser<BankNote> bankNoteDispenser;

    public ATMImpl(BankNoteDispenser<BankNote> bankNoteDispenser) throws NullPointerException {
        if (Objects.isNull(bankNoteDispenser))
            throw new IllegalArgumentException("bankNoteDispenser is null");
        this.bankNoteDispenser = bankNoteDispenser;

    }

    @Override
    public Map<BankNote, Integer> giveCash(int summToGive) {
        checkSummIsCorrect(summToGive);
        return bankNoteDispenser.giveCash(summToGive);
    }

    private void checkSummIsCorrect(int summToGive) {
        Integer remainCash = getRemainCash();
        List<BankNote> availableBanknotes = bankNoteDispenser.getCashNominals();
        if ((summToGive > getRemainCash())
                || availableBanknotes.stream().noneMatch(bankNote -> (summToGive / bankNote.getBanknoteNominal()) == 0)) {
            throw new IncorrectSummException("Запрошенная сумма больше остатка или не кратна доступным купюрам! Доступно для выдачи {} " + remainCash
                    + " Доступные купюры {} " + availableBanknotes.stream().map(bankNote -> bankNote.getBanknoteNominal().toString()).collect(Collectors.joining(", ")));
        }

    }

    @Override
    public Integer getRemainCash() {
        return bankNoteDispenser.getRemainCash()
                .entrySet()
                .stream()
                .mapToInt(value -> value.getValue() * value.getKey().getBanknoteNominal()).sum();
    }
}
