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
        this.bankNoteBaskets = Collections.unmodifiableList(bankNoteBaskets);
    }

    public BankNoteDispenserImpl() {
        this.bankNoteBaskets = Collections.emptyList();
    }

    /**
     * пытаемся отдать сумму, проходимся по всем корзинам от корзины
     * с наибольшим номиналом
     *
     * @param cashToGive сумма для выдачи
     * @return если не набрали - возвращаем пустую мапу,
     * если набрали то идем дальше и набираем мапу из корзин
     */
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
                    }
                });

        if (remainGiveCash.get() == 0 && !cashOutMap.isEmpty()) {
            return processCashOut(cashOutMap);
        }
        return new HashMap<>();
    }

    /**
     * выдаем доступные (не пустые корзины) номиналы
     *
     * @return номиналы
     */
    @Override
    public List<BankNote> getCashNominals() {
        return bankNoteBaskets.stream()
                .filter(bankNoteCashBasket -> bankNoteCashBasket.getRemainBanknotes() > 0)
                .map(BankNoteBasket::getBasketBankNoteInfo)
                .collect(Collectors.toList());
    }

    /**
     *
     * @return остаток
     */
    @Override
    public Map<BankNote, Integer> getRemainCash() {
        return countRemainCash();
    }

    /**
     * производим расход по корзинам
     *
     * @param cashOutMap карта списания
     * @return мапа с тем что списано
     */
    private Map<BankNote, Integer> processCashOut(Map<BankNoteBasket<BankNote>, Integer> cashOutMap) {
        return cashOutMap.keySet()
                .stream()
                .map(cashBasket -> dispenseFromCashBasket(cashBasket, cashOutMap.get(cashBasket)))
                .collect(Collectors.toMap(BankNoteBasket::getBasketBankNoteInfo, cashOutMap::get));
    }

    /**
     * меняем, если нашли с таким же номиналом (берем последнюю), либо добавляем корзину
     *
     * @param newBasket корзина для добавления/замены
     * @return старую, если заменили, или новую корзину
     */
    @Override
    public BankNoteBasket<BankNote> replaceOrAddBasket(BankNoteBasket<BankNote> newBasket) {
        int basketIndexForReplace = -1;
        for (int bIndex = 0; bIndex < bankNoteBaskets.size(); bIndex++) {
            if (Objects.equals(bankNoteBaskets.get(bIndex).getBasketBankNoteInfo(), newBasket.getBasketBankNoteInfo())) {
                basketIndexForReplace = bIndex;
            }
        }

        return modifyBasketList(newBasket, basketIndexForReplace);
    }

    /**
     * разворачиваем немодифицируему коллекцию, делаем то что нам нужно  и сворачиваем назад
     *
     * @param newBasket             корзина для добавления/замены
     * @param basketIndexForReplace место корзины
     * @return старую, если заменили, или новую корзину
     */
    private BankNoteBasket<BankNote> modifyBasketList(BankNoteBasket<BankNote> newBasket, int basketIndexForReplace) {
        if (basketIndexForReplace != -1) {
            BankNoteBasket<BankNote> oldBasket = bankNoteBaskets.get(basketIndexForReplace);
            List<BankNoteBasket<BankNote>> newBaskets = new ArrayList<>(bankNoteBaskets);
            newBaskets.set(basketIndexForReplace, newBasket);
            bankNoteBaskets = Collections.unmodifiableList(newBaskets);
            return oldBasket;
        } else {
            List<BankNoteBasket<BankNote>> newBaskets = new ArrayList<>(bankNoteBaskets);
            newBaskets.add(newBasket);
            bankNoteBaskets = Collections.unmodifiableList(newBaskets);
            return newBasket;
        }
    }

    // непосредственно списание из корзины
    private BankNoteBasket<BankNote> dispenseFromCashBasket(BankNoteBasket<BankNote> bankNoteBasket, Integer banknotesToDispense) {
        bankNoteBasket.getBanknotes(banknotesToDispense);
        return bankNoteBasket;
    }

    /**
     * формируем остаток
     *
     * @return остаток
     */
    private Map<BankNote, Integer> countRemainCash() {
        return bankNoteBaskets.stream().collect(Collectors.toMap(BankNoteBasket::getBasketBankNoteInfo, BankNoteBasket::getRemainBanknotes));
    }
}
