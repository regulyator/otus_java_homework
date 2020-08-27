package ru.otus.homework.atm;

import ru.otus.homework.atm.cash.BankNote;

import java.util.Map;

public interface ATM {

    Map<BankNote, Integer> giveCash(int summToGive);

    Integer getRemainCash();

}
