package ru.otus.homework.atm.atmstructure;

import ru.otus.homework.atm.cash.BankNote;

import java.util.Objects;

public class BankNoteBasketImpl implements BankNoteBasket<BankNote> {


    private final BankNote bankNote;
    private int loadedBanknotes;

    public BankNoteBasketImpl(int loadedBanknotes, BankNote bankNote) throws RuntimeException {
        if (Objects.isNull(bankNote))
            throw new IllegalArgumentException("bankNote is null!");
        if (loadedBanknotes <= 0)
            throw new IllegalArgumentException("loadedBanknotes is empty");
        this.loadedBanknotes = loadedBanknotes;
        this.bankNote = bankNote;
    }

    @Override
    public int getBanknotes(int banknotesToIssue) {
        return this.giveBanknotes(banknotesToIssue);
    }

    @Override
    public BankNote getBasketBankNoteInfo() {
        return this.bankNote;
    }

    @Override
    public int getRemainBanknotes() {
        return this.loadedBanknotes;
    }

    @Override
    public Integer getNominal() {
        return this.bankNote.getBanknoteNominal();
    }

    private int giveBanknotes(int banknotesToIssue) {
        if (banknotesToIssue > this.loadedBanknotes || banknotesToIssue <= 0) {
            return 0;
        } else {
            this.loadedBanknotes -= banknotesToIssue;
            return banknotesToIssue;
        }
    }
}
