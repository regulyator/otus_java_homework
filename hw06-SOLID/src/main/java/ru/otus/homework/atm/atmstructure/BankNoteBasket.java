package ru.otus.homework.atm.atmstructure;

import ru.otus.homework.atm.cash.BankNote;

public interface BankNoteBasket<T extends BankNote> {

    int getBanknotes(int banknotesToIssue);

    int getRemainBanknotes();

    T getBasketBankNoteInfo();

    Integer getNominal();
}
