package ru.otus.homework.atm.atmstructure;

import ru.otus.homework.atm.cash.BankNote;

import java.util.List;
import java.util.Map;

/**
 * диспенсер для выдачи денег
 *
 * @param <T>
 */
public interface BankNoteDispenser<T extends BankNote> {

    /**
     * @param cashToGive сколько выдать
     * @return выдает деньги
     */
    Map<T, Integer> giveCash(int cashToGive);

    /**
     * @return доступные номиналы
     */
    List<T> getCashNominals();

    /**
     * @return остаток
     */
    Map<T, Integer> getRemainCash();

    /**
     * меняет или если есть корзины с таким номиналом меняет какую то
     *
     * @param newBasket корзина для замены/вставки
     * @return если поменяли - то старую корзину, если добавили то новую
     */
    BankNoteBasket<T> replaceOrAddBasket(BankNoteBasket<T> newBasket);
}
