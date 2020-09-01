package ru.otus.homework.atm.atmstructure;

import ru.otus.homework.atm.cash.banknotemeta.BanknotesNominalEnum;

import java.util.List;
import java.util.Map;

/**
 * диспенсер для выдачи денег
 */
public interface BankNoteDispenser {

    /**
     * @param cashToGive сколько выдать
     * @return выдает деньги
     */
    Map<BanknotesNominalEnum, Integer> giveCash(int cashToGive);

    /**
     * @param sumToAdd сумма для добавлнения
     * @return какого номинала и сколько добавили
     */
    Map<BanknotesNominalEnum, Integer> addCash(int sumToAdd);

    /**
     * @return доступные номиналы
     */
    List<BanknotesNominalEnum> getCashNominals();

    /**
     * @return остаток
     */
    Map<BanknotesNominalEnum, Integer> getRemainCash();

    /**
     * меняет или если есть корзины с таким номиналом меняет какую то
     *
     * @param newBasket корзина для замены/вставки
     * @return если поменяли - то старую корзину, если добавили то новую
     */
    BankNoteBasket replaceOrAddBasket(BankNoteBasket newBasket);
}
