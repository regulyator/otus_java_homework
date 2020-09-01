package ru.otus.homework.atm.atmstructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.atm.cash.banknotemeta.BanknotesNominalEnum;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BankNoteDispenserTest {
    @Mock
    BankNoteBasket bankNoteBasket500;
    @Mock
    BankNoteBasket bankNoteBasket500new;
    @Mock
    BankNoteBasket bankNoteBasket100;
    @Mock
    BankNoteBasket bankNoteBasket50;
    @Mock
    BankNoteBasket bankNoteBasket5000;


    @Test
    void giveCash() {

        Mockito.when(bankNoteBasket500.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_500.getValue());
        Mockito.when(bankNoteBasket100.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_100.getValue());
        Mockito.when(bankNoteBasket50.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_50.getValue());

        Mockito.when(bankNoteBasket500.getRemainBanknotes()).then(invocation -> 100).thenAnswer(invocation -> 95);
        Mockito.when(bankNoteBasket100.getRemainBanknotes()).then(invocation -> 100);
        Mockito.when(bankNoteBasket50.getRemainBanknotes()).then(invocation -> 100);

        Mockito.when(bankNoteBasket500.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_500);
        Mockito.when(bankNoteBasket100.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_100);
        Mockito.when(bankNoteBasket50.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_50);


        BankNoteDispenser bankNoteDispenser = new BankNoteDispenserImpl(List.of(bankNoteBasket100, bankNoteBasket500, bankNoteBasket50));

        assertTrue(bankNoteDispenser.giveCash(1999550).isEmpty());
        assertTrue(bankNoteDispenser.giveCash(0).isEmpty());
        assertTrue(bankNoteDispenser.giveCash(-2).isEmpty());

        Map<BanknotesNominalEnum, Integer> resultMap = bankNoteDispenser.giveCash(2750);
        assertEquals(5, resultMap.get(BanknotesNominalEnum.BANK_NOTE_NOMINAL_500));
        assertEquals(2, resultMap.get(BanknotesNominalEnum.BANK_NOTE_NOMINAL_100));
        assertEquals(1, resultMap.get(BanknotesNominalEnum.BANK_NOTE_NOMINAL_50));

        Map<BanknotesNominalEnum, Integer> resultMap1 = bankNoteDispenser.giveCash(47500);
        assertEquals(95, resultMap1.get(BanknotesNominalEnum.BANK_NOTE_NOMINAL_500));
    }

    @Test
    void addCash() {

        Mockito.when(bankNoteBasket500.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_500.getValue());
        Mockito.when(bankNoteBasket100.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_100.getValue());
        Mockito.when(bankNoteBasket50.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_50.getValue());

        Mockito.when(bankNoteBasket500.getRemainBanknotes()).then(invocation -> 100).thenAnswer(invocation -> 95);
        Mockito.when(bankNoteBasket100.getRemainBanknotes()).then(invocation -> 100);
        Mockito.when(bankNoteBasket50.getRemainBanknotes()).then(invocation -> 100);

        Mockito.when(bankNoteBasket500.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_500);
        Mockito.when(bankNoteBasket100.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_100);
        Mockito.when(bankNoteBasket50.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_50);


        BankNoteDispenser bankNoteDispenser = new BankNoteDispenserImpl(List.of(bankNoteBasket100, bankNoteBasket500, bankNoteBasket50));

        assertTrue(bankNoteDispenser.addCash(101).isEmpty());
        assertTrue(bankNoteDispenser.addCash(0).isEmpty());
        assertTrue(bankNoteDispenser.addCash(-2).isEmpty());

        Map<BanknotesNominalEnum, Integer> resultMap = bankNoteDispenser.addCash(2750);
        assertEquals(5, resultMap.get(BanknotesNominalEnum.BANK_NOTE_NOMINAL_500));
        assertEquals(2, resultMap.get(BanknotesNominalEnum.BANK_NOTE_NOMINAL_100));
        assertEquals(1, resultMap.get(BanknotesNominalEnum.BANK_NOTE_NOMINAL_50));

        Map<BanknotesNominalEnum, Integer> resultMap1 = bankNoteDispenser.addCash(47500);
        assertEquals(95, resultMap1.get(BanknotesNominalEnum.BANK_NOTE_NOMINAL_500));
    }

    @Test
    void getCashNominals() {

        Mockito.when(bankNoteBasket500.getRemainBanknotes()).then(invocation -> 0);
        Mockito.when(bankNoteBasket100.getRemainBanknotes()).then(invocation -> 98);
        Mockito.when(bankNoteBasket50.getRemainBanknotes()).then(invocation -> 99);

        Mockito.when(bankNoteBasket100.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_100);
        Mockito.when(bankNoteBasket50.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_50);

        BankNoteDispenser bankNoteDispenser = new BankNoteDispenserImpl(List.of(bankNoteBasket100, bankNoteBasket500, bankNoteBasket50));


        assertEquals(List.of(BanknotesNominalEnum.BANK_NOTE_NOMINAL_100, BanknotesNominalEnum.BANK_NOTE_NOMINAL_50), bankNoteDispenser.getCashNominals());
    }


    @Test
    void getRemainCash() {
        Mockito.when(bankNoteBasket500.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_500.getValue());
        Mockito.when(bankNoteBasket100.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_100.getValue());
        Mockito.when(bankNoteBasket50.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_50.getValue());

        Mockito.when(bankNoteBasket500.getRemainBanknotes()).then(invocation -> 0);
        Mockito.when(bankNoteBasket100.getRemainBanknotes()).then(invocation -> 98);
        Mockito.when(bankNoteBasket50.getRemainBanknotes()).then(invocation -> 99);

        Mockito.when(bankNoteBasket500.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_500);
        Mockito.when(bankNoteBasket100.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_100);
        Mockito.when(bankNoteBasket50.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_50);

        BankNoteDispenser bankNoteDispenser = new BankNoteDispenserImpl(List.of(bankNoteBasket100, bankNoteBasket500, bankNoteBasket50));

        bankNoteDispenser.giveCash(2750);
        bankNoteDispenser.giveCash(47500);

        Map<BanknotesNominalEnum, Integer> resultRemainCash = bankNoteDispenser.getRemainCash();

        assertTrue(Objects.nonNull(resultRemainCash));

        assertEquals(0, resultRemainCash.get(BanknotesNominalEnum.BANK_NOTE_NOMINAL_500));
        assertEquals(98, resultRemainCash.get(BanknotesNominalEnum.BANK_NOTE_NOMINAL_100));
        assertEquals(99, resultRemainCash.get(BanknotesNominalEnum.BANK_NOTE_NOMINAL_50));
    }

    @Test
    void testNullConstructorArgument() {
        assertThrows(IllegalArgumentException.class, () -> new BankNoteDispenserImpl(null));

    }

    @Test
    void replaceOrAddBasket() {
        Mockito.when(bankNoteBasket500.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_500);
        Mockito.when(bankNoteBasket500new.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_500);
        Mockito.when(bankNoteBasket5000.getBasketBankNoteInfo()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_5000);

        BankNoteDispenser bankNoteDispenser = new BankNoteDispenserImpl(List.of(bankNoteBasket50, bankNoteBasket100, bankNoteBasket500));

        assertEquals(bankNoteBasket500, bankNoteDispenser.replaceOrAddBasket(bankNoteBasket500new));
        assertEquals(bankNoteBasket5000, bankNoteDispenser.replaceOrAddBasket(bankNoteBasket5000));

    }


}