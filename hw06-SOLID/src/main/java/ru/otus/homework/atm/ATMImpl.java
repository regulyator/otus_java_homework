package ru.otus.homework.atm;

import ru.otus.homework.atm.atmstructure.BankNoteDispenser;
import ru.otus.homework.atm.cash.banknotemeta.BanknotesNominalEnum;
import ru.otus.homework.atm.exceptions.IncorrectSumOrNominalException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ATMImpl implements ATM {

    private final BankNoteDispenser bankNoteDispenser;

    /**
     * инициализация банкомата, без диспенсера выкидываем исключение
     *
     * @param bankNoteDispenser диспенсер
     */
    public ATMImpl(BankNoteDispenser bankNoteDispenser) {
        if (Objects.isNull(bankNoteDispenser))
            throw new IllegalArgumentException("bankNoteDispenser is null");
        this.bankNoteDispenser = bankNoteDispenser;

    }

    @Override
    public Map<BanknotesNominalEnum, Integer> giveCash(int sum) throws IncorrectSumOrNominalException {
        checkSumIsCorrect(sum, false);
        return bankNoteDispenser.giveCash(sum);
    }

    @Override
    public Map<BanknotesNominalEnum, Integer> addCash(int sum) {
        checkSumIsCorrect(sum, true);
        return bankNoteDispenser.addCash(sum);
    }

    @Override
    public Integer getRemainCash() {
        //получаем из диспенсера мапу с тем что в нем и считаем сумму
        return bankNoteDispenser.getRemainCash()
                .entrySet()
                .stream()
                .mapToInt(value -> value.getValue() * value.getKey().getValue()).sum();
    }

    /**
     * проверяем на корректность введенную сумму если мы не можем ее выдать или добавить - выкидываем исключение
     *
     * @param sum      сумма
     * @param isForAdd операция пополнения
     */
    private void checkSumIsCorrect(int sum, boolean isForAdd) throws IncorrectSumOrNominalException {
        Integer remainCash = getRemainCash();
        List<BanknotesNominalEnum> availableBanknotes = bankNoteDispenser.getCashNominals();
        if (isForAdd) {
            if (availableBanknotes.stream().noneMatch(bankNote -> (sum % bankNote.getValue()) == 0)) {
                throw new IncorrectSumOrNominalException("Введенная сумма не может быть внесена! "
                        + "Доступные купюры для пополнения: " + availableBanknotes.stream().map(bankNote -> String.valueOf(bankNote.getValue())).collect(Collectors.joining(", ")));
            }
        } else {
            if ((sum > getRemainCash())
                    || availableBanknotes.stream().noneMatch(bankNote -> (sum % bankNote.getValue()) == 0)) {
                throw new IncorrectSumOrNominalException("Запрошенная сумма больше остатка или не кратна доступным купюрам! Доступно для выдачи: " + remainCash
                        + " Доступные купюры: " + availableBanknotes.stream().map(bankNote -> String.valueOf(bankNote.getValue())).collect(Collectors.joining(", ")));
            }
        }


    }
}
