package ru.otus.homework.atm;

import ru.otus.homework.atm.atmstructure.CashBasket;
import ru.otus.homework.atm.atmstructure.CashBasketImpl;
import ru.otus.homework.atm.model.CashNominalEnum;

public class ATMApp {
    public static void main(String[] args) {
        CashBasket<CashNominalEnum> cashBasket5000 = new CashBasketImpl(100, CashNominalEnum.CASH_NOMINAL_1000);

        System.out.println(cashBasket5000.getBasketNominal());

        System.out.println(cashBasket5000.getBanknotes(80));
        System.out.println(cashBasket5000.getBanknotes(20));
        System.out.println(cashBasket5000.getBanknotes(20));
        int r = cashBasket5000.getRemainBanknotes();
        r = 10;
        System.out.println(cashBasket5000.getBanknotes(1));

    }
}
