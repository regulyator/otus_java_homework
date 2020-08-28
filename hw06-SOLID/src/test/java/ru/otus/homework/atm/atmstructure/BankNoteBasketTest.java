package ru.otus.homework.atm.atmstructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.homework.atm.cash.banknotemeta.BanknotesNominalEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankNoteBasketTest {
    private BankNoteBasket bankNoteBasket;

    @BeforeEach
    void setUp() {
        bankNoteBasket = new BankNoteBasketImpl(100, BanknotesNominalEnum.BANK_NOTE_NOMINAL_500);
    }

    @Test
    void getBanknotes() {


        assertEquals(0, bankNoteBasket.getBanknotes(101));
        assertEquals(0, bankNoteBasket.getBanknotes(-1));
        assertEquals(20, bankNoteBasket.getBanknotes(20));
        assertEquals(30, bankNoteBasket.getBanknotes(30));
        assertEquals(0, bankNoteBasket.getBanknotes(51));
        assertEquals(50, bankNoteBasket.getBanknotes(50));
        assertEquals(0, bankNoteBasket.getBanknotes(1));
    }

    @Test
    void addBanknotes() {
        BankNoteBasket bankNoteBasket500 = new BankNoteBasketImpl(100, BanknotesNominalEnum.BANK_NOTE_NOMINAL_500);
        bankNoteBasket500.addBanknotes(10);
        assertEquals(110, bankNoteBasket500.getRemainBanknotes());
        bankNoteBasket500.addBanknotes(0);
        assertEquals(110, bankNoteBasket500.getRemainBanknotes());
        bankNoteBasket500.addBanknotes(-12);
        assertEquals(110, bankNoteBasket500.getRemainBanknotes());
    }

    @Test
    void getRemainBanknotes() {
        assertEquals(100, bankNoteBasket.getRemainBanknotes());
        bankNoteBasket.getBanknotes(30);
        assertEquals(70, bankNoteBasket.getRemainBanknotes());
        bankNoteBasket.getBanknotes(20);
        assertEquals(50, bankNoteBasket.getRemainBanknotes());
        bankNoteBasket.getBanknotes(1);
        assertEquals(49, bankNoteBasket.getRemainBanknotes());
        bankNoteBasket.getBanknotes(9);
        assertEquals(40, bankNoteBasket.getRemainBanknotes());
        bankNoteBasket.getBanknotes(99);
        assertEquals(40, bankNoteBasket.getRemainBanknotes());
    }

    @Test
    void getBasketCashInfo() {
        assertEquals(BanknotesNominalEnum.BANK_NOTE_NOMINAL_500, bankNoteBasket.getBasketBankNoteInfo());
        assertEquals(BanknotesNominalEnum.BANK_NOTE_NOMINAL_500.getValue(), bankNoteBasket.getBasketBankNoteInfo().getValue());
    }

    @Test
    void getNominal() {
        assertEquals(BanknotesNominalEnum.BANK_NOTE_NOMINAL_500.getValue(), bankNoteBasket.getNominal());
    }

    @Test
    void testNullConstructorArgument() {
        assertThrows(IllegalArgumentException.class, () -> new BankNoteBasketImpl(10, null));
        assertThrows(IllegalArgumentException.class, () -> new BankNoteBasketImpl(0, BanknotesNominalEnum.BANK_NOTE_NOMINAL_500));
        assertThrows(IllegalArgumentException.class, () -> new BankNoteBasketImpl(-2, BanknotesNominalEnum.BANK_NOTE_NOMINAL_500));

    }


}