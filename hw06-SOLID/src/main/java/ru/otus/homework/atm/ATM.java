package ru.otus.homework.atm;

import ru.otus.homework.atm.model.CashNominalEnum;

import java.util.Map;

public interface ATM<T extends CashNominalEnum> {

    Map<T, Integer> giveCash(int cashToGive);

    Map<T, Integer> getRemainCash();

}
