package ru.otus.homework.atm.atmstructure;

import ru.otus.homework.atm.model.CashNominalEnum;

public class CashBasketImpl implements CashBasket<CashNominalEnum> {


    private int loadedBanknotes;
    private final CashNominalEnum cashNominalEnum;

    public CashBasketImpl(int loadedBanknotes, CashNominalEnum cashNominalEnum) {
        this.loadedBanknotes = loadedBanknotes;
        this.cashNominalEnum = cashNominalEnum;
    }

    @Override
    public int getBanknotes(int banknotesToIssue) {
        return this.giveBanknotes(banknotesToIssue);
    }

    @Override
    public CashNominalEnum getBasketNominal() {
        return this.cashNominalEnum;
    }

    @Override
    public int getRemainBanknotes() {
        return this.loadedBanknotes;
    }

    private int giveBanknotes(int banknotesToIssue) {
        int remainBanknotes = this.loadedBanknotes;
        if (banknotesToIssue > this.loadedBanknotes) {
            this.loadedBanknotes = 0;
            return remainBanknotes;
        } else {
            this.loadedBanknotes -= banknotesToIssue;
            return banknotesToIssue;
        }


    }
}
