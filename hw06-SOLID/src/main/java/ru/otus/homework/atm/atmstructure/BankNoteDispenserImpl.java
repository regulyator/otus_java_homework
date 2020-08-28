package ru.otus.homework.atm.atmstructure;


import ru.otus.homework.atm.cash.banknotemeta.BanknotesNominalEnum;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BankNoteDispenserImpl implements BankNoteDispenser {

    private List<BankNoteBasket> bankNoteBaskets;

    public BankNoteDispenserImpl(List<BankNoteBasket> bankNoteBaskets) throws NullPointerException {
        if (Objects.isNull(bankNoteBaskets))
            throw new IllegalArgumentException("bankNoteBaskets is null");
        this.bankNoteBaskets = Collections.unmodifiableList(bankNoteBaskets);
    }

    public BankNoteDispenserImpl() {
        this.bankNoteBaskets = Collections.emptyList();
    }


    /**
     * @param cashToGive сколько выдать
     * @return если не набрали - возвращаем пустую мапу,
     * * если набрали то идем дальше и набираем мапу из корзин
     */
    @Override
    public Map<BanknotesNominalEnum, Integer> giveCash(int cashToGive) {
        return processBanknotesOperation(cashToGive, false);
    }

    /**
     * @param sumToAdd сумма для добавлнения
     * @return если не раскидать по корзинам - возвращаем пустую мапу и ничего не добавляем,
     * * если набрали то идем дальше и набираем мапу из корзи
     */
    @Override
    public Map<BanknotesNominalEnum, Integer> addCash(int sumToAdd) {
        return processBanknotesOperation(sumToAdd, true);
    }

    /**
     * пытаемся отдать или добавить сумму, проходимся по всем корзинам от корзины
     * с наибольшим номиналом
     *
     * @param cashToGive сумма для выдачи/добавления
     * @return если не набрали - возвращаем пустую мапу,
     * если набрали то идем дальше и набираем мапу из корзин
     */
    private Map<BanknotesNominalEnum, Integer> processBanknotesOperation(int cashToGive, boolean isAddOperation) {
        Map<BankNoteBasket, Integer> cashProcessMap = new HashMap<>();
        AtomicInteger remainProcessCash = new AtomicInteger(cashToGive);
        bankNoteBaskets
                .stream()
                .sorted(Comparator.comparingInt(BankNoteBasket::getNominal).reversed())
                .forEach(cashBasket -> {
                    int divideResult = remainProcessCash.get() / cashBasket.getNominal();
                    if (divideResult > 0 && divideResult <= cashBasket.getRemainBanknotes()) {
                        cashProcessMap.put(cashBasket, divideResult);
                        remainProcessCash.set(remainProcessCash.get() - (divideResult * cashBasket.getNominal()));
                    }
                });

        if (remainProcessCash.get() == 0 && !cashProcessMap.isEmpty()) {
            return processCash(cashProcessMap, isAddOperation);
        }
        return new HashMap<>();
    }

    /**
     * выдаем доступные (не пустые корзины) номиналы
     *
     * @return номиналы
     */
    @Override
    public List<BanknotesNominalEnum> getCashNominals() {
        return bankNoteBaskets.stream()
                .filter(bankNoteCashBasket -> bankNoteCashBasket.getRemainBanknotes() > 0)
                .map(BankNoteBasket::getBasketBankNoteInfo)
                .collect(Collectors.toList());
    }

    /**
     * @return остаток
     */
    @Override
    public Map<BanknotesNominalEnum, Integer> getRemainCash() {
        return countRemainCash();
    }

    /**
     * производим расход по корзинам
     *
     * @param cashOutMap карта списания
     * @return мапа с тем что списано
     */
    private Map<BanknotesNominalEnum, Integer> processCash(Map<BankNoteBasket, Integer> cashOutMap, boolean isAddOperation) {
        return cashOutMap.keySet()
                .stream()
                .peek(cashBasket -> {
                    if (isAddOperation) {
                        cashBasket.addBanknotes(cashOutMap.get(cashBasket));
                    } else {
                        cashBasket.getBanknotes(cashOutMap.get(cashBasket));
                    }
                })
                .collect(Collectors.toMap(BankNoteBasket::getBasketBankNoteInfo, cashOutMap::get));
    }

    /**
     * меняем, если нашли с таким же номиналом (берем последнюю), либо добавляем корзину
     *
     * @param newBasket корзина для добавления/замены
     * @return старую, если заменили, или новую корзину
     */
    @Override
    public BankNoteBasket replaceOrAddBasket(BankNoteBasket newBasket) {
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
    private BankNoteBasket modifyBasketList(BankNoteBasket newBasket, int basketIndexForReplace) {
        if (basketIndexForReplace != -1) {
            BankNoteBasket oldBasket = bankNoteBaskets.get(basketIndexForReplace);
            List<BankNoteBasket> newBaskets = new ArrayList<>(bankNoteBaskets);
            newBaskets.set(basketIndexForReplace, newBasket);
            bankNoteBaskets = Collections.unmodifiableList(newBaskets);
            return oldBasket;
        } else {
            List<BankNoteBasket> newBaskets = new ArrayList<>(bankNoteBaskets);
            newBaskets.add(newBasket);
            bankNoteBaskets = Collections.unmodifiableList(newBaskets);
            return newBasket;
        }
    }

    /**
     * формируем остаток
     *
     * @return остаток
     */
    private Map<BanknotesNominalEnum, Integer> countRemainCash() {
        return bankNoteBaskets.stream().collect(Collectors.toMap(BankNoteBasket::getBasketBankNoteInfo, BankNoteBasket::getRemainBanknotes));
    }
}
