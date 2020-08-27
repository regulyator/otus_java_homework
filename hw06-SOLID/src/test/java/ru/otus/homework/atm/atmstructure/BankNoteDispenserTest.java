package ru.otus.homework.atm.atmstructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.atm.cash.BankNote;
import ru.otus.homework.atm.cash.banknotemeta.BanknotesNominalEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BankNoteDispenserTest {
    @Mock
    BankNoteBasket<BankNote> bankNoteBasket500;
    @Mock
    BankNoteBasket<BankNote> bankNoteBasket500new;
    @Mock
    BankNoteBasket<BankNote> bankNoteBasket100;
    @Mock
    BankNoteBasket<BankNote> bankNoteBasket50;
    @Mock
    BankNoteBasket<BankNote> bankNoteBasket5000;
    @Mock
    BankNote bankNote5000;
    @Mock
    BankNote bankNote500;
    @Mock
    BankNote bankNote100;
    @Mock
    BankNote bankNote50;


    @Test
    void giveCash() {

        Mockito.when(bankNoteBasket500.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_500.getValue().value());
        Mockito.when(bankNoteBasket100.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_100.getValue().value());
        Mockito.when(bankNoteBasket50.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_50.getValue().value());

        Mockito.when(bankNoteBasket500.getRemainBanknotes()).then(invocation -> 100).thenAnswer(invocation -> 95);
        Mockito.when(bankNoteBasket100.getRemainBanknotes()).then(invocation -> 100);
        Mockito.when(bankNoteBasket50.getRemainBanknotes()).then(invocation -> 100);

        Mockito.when(bankNoteBasket500.getBasketBankNoteInfo()).then(invocation -> bankNote500);
        Mockito.when(bankNoteBasket100.getBasketBankNoteInfo()).then(invocation -> bankNote100);
        Mockito.when(bankNoteBasket50.getBasketBankNoteInfo()).then(invocation -> bankNote50);

        BankNoteDispenser<BankNote> bankNoteDispenser = new BankNoteDispenserImpl(List.of(bankNoteBasket100, bankNoteBasket500, bankNoteBasket50));

        assertTrue(bankNoteDispenser.giveCash(1999550).isEmpty());
        assertTrue(bankNoteDispenser.giveCash(0).isEmpty());
        assertTrue(bankNoteDispenser.giveCash(-2).isEmpty());

        Map<BankNote, Integer> resultMap = bankNoteDispenser.giveCash(2750);
        assertEquals(5, resultMap.get(bankNote500));
        assertEquals(2, resultMap.get(bankNote100));
        assertEquals(1, resultMap.get(bankNote50));

        Map<BankNote, Integer> resultMap1 = bankNoteDispenser.giveCash(47500);
        assertEquals(95, resultMap1.get(bankNote500));
    }

    @Test
    void getCashNominals() {

        Mockito.when(bankNoteBasket500.getRemainBanknotes()).then(invocation -> 0);
        Mockito.when(bankNoteBasket100.getRemainBanknotes()).then(invocation -> 98);
        Mockito.when(bankNoteBasket50.getRemainBanknotes()).then(invocation -> 99);

        Mockito.when(bankNoteBasket100.getBasketBankNoteInfo()).then(invocation -> bankNote100);
        Mockito.when(bankNoteBasket50.getBasketBankNoteInfo()).then(invocation -> bankNote50);

        BankNoteDispenser<BankNote> bankNoteDispenser = new BankNoteDispenserImpl(List.of(bankNoteBasket100, bankNoteBasket500, bankNoteBasket50));


        assertEquals(List.of(bankNote100, bankNote50), bankNoteDispenser.getCashNominals());
    }


    @Test
    void getRemainCash() {
        Mockito.when(bankNoteBasket500.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_500.getValue().value());
        Mockito.when(bankNoteBasket100.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_100.getValue().value());
        Mockito.when(bankNoteBasket50.getNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_50.getValue().value());

        Mockito.when(bankNoteBasket500.getRemainBanknotes()).then(invocation -> 0);
        Mockito.when(bankNoteBasket100.getRemainBanknotes()).then(invocation -> 98);
        Mockito.when(bankNoteBasket50.getRemainBanknotes()).then(invocation -> 99);

        Mockito.when(bankNoteBasket500.getBasketBankNoteInfo()).then(invocation -> bankNote500);
        Mockito.when(bankNoteBasket100.getBasketBankNoteInfo()).then(invocation -> bankNote100);
        Mockito.when(bankNoteBasket50.getBasketBankNoteInfo()).then(invocation -> bankNote50);

        BankNoteDispenser<BankNote> bankNoteDispenser = new BankNoteDispenserImpl(List.of(bankNoteBasket100, bankNoteBasket500, bankNoteBasket50));

        bankNoteDispenser.giveCash(2750);
        bankNoteDispenser.giveCash(47500);

        Map<BankNote, Integer> resultRemainCash = bankNoteDispenser.getRemainCash();

        assertTrue(Objects.nonNull(resultRemainCash));

        assertEquals(0, resultRemainCash.get(bankNote500));
        assertEquals(98, resultRemainCash.get(bankNote100));
        assertEquals(99, resultRemainCash.get(bankNote50));
    }

    @Test
    void testNullConstructorArgument() {
        assertThrows(IllegalArgumentException.class, () -> new BankNoteDispenserImpl(null));

    }

    @Test
    void replaceOrAddBasket() {
        Mockito.when(bankNoteBasket500.getBasketBankNoteInfo()).then(invocation -> bankNote500);
        Mockito.when(bankNoteBasket500new.getBasketBankNoteInfo()).then(invocation -> bankNote500);
        Mockito.when(bankNoteBasket5000.getBasketBankNoteInfo()).then(invocation -> bankNote5000);

        List<BankNoteBasket<BankNote>> baskets = new ArrayList<>();
        baskets.add(bankNoteBasket100);
        baskets.add(bankNoteBasket500);
        baskets.add(bankNoteBasket50);

        BankNoteDispenser<BankNote> bankNoteDispenser = new BankNoteDispenserImpl(baskets);

        assertEquals(bankNoteBasket500, bankNoteDispenser.replaceOrAddBasket(bankNoteBasket500new));
        assertEquals(bankNoteBasket5000, bankNoteDispenser.replaceOrAddBasket(bankNoteBasket5000));

    }
}