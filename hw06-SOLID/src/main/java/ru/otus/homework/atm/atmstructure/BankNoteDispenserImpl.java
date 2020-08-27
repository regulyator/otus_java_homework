package ru.otus.homework.atm.atmstructure;

import ru.otus.homework.atm.cash.BankNote;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BankNoteDispenserImpl implements BankNoteDispenser<BankNote> {

    private List<BankNoteBasket<BankNote>> bankNoteBaskets;

    public BankNoteDispenserImpl(List<BankNoteBasket<BankNote>> bankNoteBaskets) throws NullPointerException {
        if (Objects.isNull(bankNoteBaskets))
            throw new IllegalArgumentException("bankNoteBaskets is null");
        this.bankNoteBaskets = bankNoteBaskets;
    }

    @Override
    public Map<BankNote, Integer> giveCash(int cashToGive) {
        Map<BankNoteBasket<BankNote>, Integer> cashOutMap = new HashMap<>();
        AtomicInteger remainGiveCash = new AtomicInteger(cashToGive);
        bankNoteBaskets
                .stream()
                .sorted(Comparator.comparingInt(BankNoteBasket<BankNote>::getNominal).reversed())
                .forEach(cashBasket -> {
                    int divideResult = remainGiveCash.get() / cashBasket.getNominal();
                    if (divideResult > 0 && divideResult <= cashBasket.getRemainBanknotes()) {
                        cashOutMap.put(cashBasket, divideResult);
                        remainGiveCash.set(remainGiveCash.get() - (divideResult * cashBasket.getNominal()));
                        System.out.println(cashBasket.getNominal() + "::" + divideResult);
                    }
                });

        if (remainGiveCash.get() == 0 && !cashOutMap.isEmpty()) {
            return processCashOut(cashOutMap);
        }
        return new HashMap<>();
    }

    @Override
    public List<BankNote> getCashNominals() {
        return bankNoteBaskets.stream()
                .filter(bankNoteCashBasket -> bankNoteCashBasket.getRemainBanknotes() > 0)
                .map(BankNoteBasket::getBasketBankNoteInfo)
                .collect(Collectors.toList());
    }

    @Override
    public Map<BankNote, Integer> getRemainCash() {
        return countRemainCash();
    }

    private Map<BankNote, Integer> processCashOut(Map<BankNoteBasket<BankNote>, Integer> cashOutMap) {
        return cashOutMap.keySet()
                .stream()
                .map(cashBasket -> dispenseFromCashBasket(cashBasket, cashOutMap.get(cashBasket)))
                .collect(Collectors.toMap(BankNoteBasket::getBasketBankNoteInfo, cashOutMap::get));
    }

    @Override
    public BankNoteBasket<BankNote> replaceOrAddBasket(BankNoteBasket<BankNote> newBasket) {
        int basketIndexForReplace = -1;
        for (int bIndex = 0; bIndex < bankNoteBaskets.size(); bIndex++) {
            if (Objects.equals(bankNoteBaskets.get(bIndex).getBasketBankNoteInfo(), newBasket.getBasketBankNoteInfo())) {
                basketIndexForReplace = bIndex;
            }
        }

        if (basketIndexForReplace >= 0) {
            BankNoteBasket<BankNote> oldBasket = bankNoteBaskets.get(basketIndexForReplace);
            bankNoteBaskets.set(basketIndexForReplace, newBasket);
            return oldBasket;
        } else {
            bankNoteBaskets.add(newBasket);
            return newBasket;
        }
    }

    private BankNoteBasket<BankNote> dispenseFromCashBasket(BankNoteBasket<BankNote> bankNoteBasket, Integer banknotesToDispense) {
        bankNoteBasket.getBanknotes(banknotesToDispense);
        return bankNoteBasket;
    }

    private Map<BankNote, Integer> countRemainCash() {
        return bankNoteBaskets.stream().collect(Collectors.toMap(BankNoteBasket::getBasketBankNoteInfo, BankNoteBasket::getRemainBanknotes));
    }
}
