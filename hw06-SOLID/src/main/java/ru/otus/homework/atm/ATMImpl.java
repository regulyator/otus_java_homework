package ru.otus.homework.atm;

import ru.otus.homework.atm.atmstructure.BankNoteDispenser;
import ru.otus.homework.atm.cash.BankNote;
import ru.otus.homework.atm.exceptions.IncorrectSumOrNominalException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ATMImpl implements ATM {

    private final BankNoteDispenser<BankNote> bankNoteDispenser;

    /**
     * инициализация банкомата, без диспенсера выкидываем исключение
     *
     * @param bankNoteDispenser диспенсер
     */
    public ATMImpl(BankNoteDispenser<BankNote> bankNoteDispenser) {
        if (Objects.isNull(bankNoteDispenser))
            throw new IllegalArgumentException("bankNoteDispenser is null");
        this.bankNoteDispenser = bankNoteDispenser;

    }

    @Override
    public Map<BankNote, Integer> giveCash(int summToGive) throws IncorrectSumOrNominalException {
        checkSummIsCorrect(summToGive);
        return bankNoteDispenser.giveCash(summToGive);
    }

    @Override
    public Integer getRemainCash() {
        //получаем из диспенсера мапу с тем что в нем и считаем сумму
        return bankNoteDispenser.getRemainCash()
                .entrySet()
                .stream()
                .mapToInt(value -> value.getValue() * value.getKey().getBanknoteNominal()).sum();
    }

    /**
     * проверяем на корректность введенную сумму если мы не можем ее выдать по причине что нет номиналов или сумма больше остатка - выкидываем исключение
     *
     * @param sumToGive сумма
     */
    private void checkSummIsCorrect(int sumToGive) throws IncorrectSumOrNominalException {
        Integer remainCash = getRemainCash();
        List<BankNote> availableBanknotes = bankNoteDispenser.getCashNominals();
        if ((sumToGive > getRemainCash())
                || availableBanknotes.stream().noneMatch(bankNote -> (sumToGive % bankNote.getBanknoteNominal()) == 0)) {
            throw new IncorrectSumOrNominalException("Запрошенная сумма больше остатка или не кратна доступным купюрам! Доступно для выдачи: " + remainCash
                    + " Доступные купюры: " + availableBanknotes.stream().map(bankNote -> bankNote.getBanknoteNominal().toString()).collect(Collectors.joining(", ")));
        }

    }
}
