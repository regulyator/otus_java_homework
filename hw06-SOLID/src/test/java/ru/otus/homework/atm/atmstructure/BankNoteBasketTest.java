package ru.otus.homework.atm.atmstructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.homework.atm.cash.BankNote;
import ru.otus.homework.atm.cash.banknotemeta.BanknotesNominalEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankNoteBasketTest {
    private BankNoteBasket<BankNote> bankNoteBasket;
    private BankNote bankNote;

    @BeforeEach
    void setUp() {
        bankNote = Mockito.mock(BankNote.class);
        Mockito.when(bankNote.getBanknoteNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_500.getValue().value());

        bankNoteBasket = new BankNoteBasketImpl(100, bankNote);
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
        assertEquals(bankNote, bankNoteBasket.getBasketBankNoteInfo());
        assertEquals(bankNote.getBanknoteNominal(), bankNoteBasket.getBasketBankNoteInfo().getBanknoteNominal());
    }

    @Test
    void getNominal() {
        assertEquals(BanknotesNominalEnum.BANK_NOTE_NOMINAL_500.getValue().value(), bankNoteBasket.getNominal());
    }

    @Test
    void testNullConstructorArgument() {
        assertThrows(IllegalArgumentException.class, () -> new BankNoteBasketImpl(10,null));
        assertThrows(IllegalArgumentException.class, () -> new BankNoteBasketImpl(0,bankNote));
        assertThrows(IllegalArgumentException.class, () -> new BankNoteBasketImpl(-2,bankNote));

    }
}