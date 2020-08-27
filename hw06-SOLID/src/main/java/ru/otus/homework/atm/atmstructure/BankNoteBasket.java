package ru.otus.homework.atm.atmstructure;

import ru.otus.homework.atm.cash.BankNote;

/**
 * Корзина для купюр
 *
 * @param <T>
 */
public interface BankNoteBasket<T extends BankNote> {

    /**
     * выдаем купюры
     *
     * @param banknotesToIssue сколько нужно
     * @return сколько дали
     */
    int getBanknotes(int banknotesToIssue);

    /**
     * @return остаток
     */
    int getRemainBanknotes();

    /**
     * @return тип банкнот
     */
    T getBasketBankNoteInfo();

    /**
     * @return номинал банкнот
     */
    Integer getNominal();
}
