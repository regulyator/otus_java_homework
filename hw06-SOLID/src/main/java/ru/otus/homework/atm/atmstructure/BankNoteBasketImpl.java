package ru.otus.homework.atm.atmstructure;

import ru.otus.homework.atm.cash.banknotemeta.BanknotesNominalEnum;

import java.util.Objects;

/**
 * корзина не дозагружается, т.е. нужно сразу запонять
 */
public class BankNoteBasketImpl implements BankNoteBasket {

    private final BanknotesNominalEnum bankNote;
    private int loadedBanknotes;

    public BankNoteBasketImpl(int loadedBanknotes, BanknotesNominalEnum bankNote) throws RuntimeException {
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
    public int addBanknotes(int banknotesToAdd) {
        return this.putBanknotes(banknotesToAdd);
    }

    @Override
    public BanknotesNominalEnum getBasketBankNoteInfo() {
        return this.bankNote;
    }

    @Override
    public int getRemainBanknotes() {
        return this.loadedBanknotes;
    }

    @Override
    public Integer getNominal() {
        return this.bankNote.getValue();
    }

    /**
     * небольшая проверка что не вылезаем за доступный лимит
     *
     * @param banknotesToIssue сколько нужно выдать
     * @return если проверку прошли - выдаем сколько нужно, если нет, то ничего не выдаем
     */
    private int giveBanknotes(int banknotesToIssue) {
        if (banknotesToIssue > this.loadedBanknotes || banknotesToIssue <= 0) {
            return 0;
        } else {
            this.loadedBanknotes -= banknotesToIssue;
            return banknotesToIssue;
        }
    }

    /**
     * небольшая проверка что можем добавить
     *
     * @param banknotesToAdd сколько нужно добавить
     * @return если проверку прошли - выдаем добавляем нужно, если нет, то ничего не добавляем
     */
    private int putBanknotes(int banknotesToAdd) {
        if (banknotesToAdd <= 0) {
            return 0;
        } else {
            loadedBanknotes += banknotesToAdd;
            return banknotesToAdd;
        }
    }
}
