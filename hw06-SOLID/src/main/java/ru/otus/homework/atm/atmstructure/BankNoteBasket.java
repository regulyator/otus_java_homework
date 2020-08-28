package ru.otus.homework.atm.atmstructure;

import ru.otus.homework.atm.cash.banknotemeta.BanknotesNominalEnum;

/**
 * Корзина для купюр
 */
public interface BankNoteBasket {

    /**
     * выдаем купюры
     *
     * @param banknotesToIssue сколько нужно
     * @return сколько дали
     */
    int getBanknotes(int banknotesToIssue);

    /**
     * добавляем купюры
     *
     * @param banknotesToAdd купюры для добавлнения
     */
    int addBanknotes(int banknotesToAdd);

    /**
     * @return остаток
     */
    int getRemainBanknotes();

    /**
     * @return тип банкнот
     */
    BanknotesNominalEnum getBasketBankNoteInfo();

    /**
     * @return номинал банкнот
     */
    Integer getNominal();
}
