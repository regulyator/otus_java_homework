package ru.otus.homework.atm;

import ru.otus.homework.atm.cash.banknotemeta.BanknotesNominalEnum;

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
    Map<BanknotesNominalEnum, Integer> giveCash(int sumToGive);

    /**
     * @param sumToAdd сумма для добавления
     * @return мапу с купюрами и количеством купюр
     */
    Map<BanknotesNominalEnum, Integer> addCash(int sumToAdd);

    /**
     * возвращает весь доступный достаток в ячейках
     *
     * @return сумму
     */
    Integer getRemainCash();


}
