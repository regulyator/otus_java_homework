package ru.otus.homework.atm;

import ru.otus.homework.atm.cash.BankNote;

import java.util.Map;

/**
 * А это сам банкомат
 */
public interface ATM {

    /**
     * пробует выдать деньги
     *
     * @param sumToGive сумма для выдачи
     * @return мапу с купюрами и количеством купюр
     */
    Map<BankNote, Integer> giveCash(int sumToGive);

    /**
     * возвращает весь доступный достаток в ячейках
     *
     * @return сумму
     */
    Integer getRemainCash();

}
