package ru.otus.homework.atm.atmstructure;

import ru.otus.homework.atm.model.CashNominalEnum;

public interface CashBasket<T extends CashNominalEnum> {

    int getBanknotes(int banknotesToIssue);

    int getRemainBanknotes();

    T getBasketNominal();
}
