package ru.otus.homework.atm;

import ru.otus.homework.atm.atmstructure.BankNoteBasketImpl;
import ru.otus.homework.atm.atmstructure.BankNoteDispenser;
import ru.otus.homework.atm.atmstructure.BankNoteDispenserImpl;
import ru.otus.homework.atm.cash.BankNote;
import ru.otus.homework.atm.cash.BankNoteImpl;
import ru.otus.homework.atm.cash.banknotemeta.BanknotesNominalEnum;
import ru.otus.homework.atm.exceptions.IncorrectSumOrNominalException;

public class ATMApp {
    public static void main(String[] args) {

        //запоняем диспенсер
        BankNoteDispenser<BankNote> dispenser = new BankNoteDispenserImpl();
        dispenser.replaceOrAddBasket(new BankNoteBasketImpl(100, new BankNoteImpl(BanknotesNominalEnum.BANK_NOTE_NOMINAL_1000)));
        dispenser.replaceOrAddBasket(new BankNoteBasketImpl(100, new BankNoteImpl(BanknotesNominalEnum.BANK_NOTE_NOMINAL_500)));
        dispenser.replaceOrAddBasket(new BankNoteBasketImpl(100, new BankNoteImpl(BanknotesNominalEnum.BANK_NOTE_NOMINAL_5000)));

        // Инициализируем банкомат
        ATM atm = new ATMImpl(dispenser);
        //запрос остатка
        System.out.println(atm.getRemainCash());
        //снимаем корректно
        atm.giveCash(100_500)
                .forEach((bankNote, count) -> System.out.println("Купюр " + bankNote.getBanknoteNominal() + " - " + count + " шт."));
        //запрос остатка
        System.out.println(atm.getRemainCash());
        //снимаем некорректно
        try {
            atm.giveCash(100_000_000)
                    .forEach((bankNote, count) -> System.out.println("Купюр " + bankNote.getBanknoteNominal() + " - " + count + " шт."));
        } catch (IncorrectSumOrNominalException ex) {
            System.out.println(ex.getMessage());
        }

        //снимаем некорректно
        try {
            atm.giveCash(100_000_790)
                    .forEach((bankNote, count) -> System.out.println("Купюр " + bankNote.getBanknoteNominal() + " - " + count + " шт."));
        } catch (IncorrectSumOrNominalException ex) {
            System.out.println(ex.getMessage());
        }
        //запрос остатка
        System.out.println(atm.getRemainCash());
        //добавляем/меняем корзину
        dispenser.replaceOrAddBasket(new BankNoteBasketImpl(100, new BankNoteImpl(BanknotesNominalEnum.BANK_NOTE_NOMINAL_5000)));
        //запрос остатка
        System.out.println(atm.getRemainCash());
    }
}
