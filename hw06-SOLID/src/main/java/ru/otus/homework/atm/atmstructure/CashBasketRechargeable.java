package ru.otus.homework.atm.atmstructure;

import ru.otus.homework.atm.model.CashNominalEnum;

public interface CashBasketRechargeable<T extends CashNominalEnum> extends CashBasket<T> {

    void addBanknotes(int banknotesToAdd);

}
