package ru.otus.homework.atm.atmstructure;

import ru.otus.homework.atm.cash.BankNote;

import java.util.List;
import java.util.Map;

public interface BankNoteDispenser<T extends BankNote> {

    Map<T, Integer> giveCash(int cashToGive);

    List<T> getCashNominals();

    Map<T, Integer> getRemainCash();

    BankNoteBasket<T> replaceOrAddBasket(BankNoteBasket<T> newBasket);
}
