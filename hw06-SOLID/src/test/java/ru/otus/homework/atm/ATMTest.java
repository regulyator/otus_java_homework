package ru.otus.homework.atm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.atm.atmstructure.BankNoteBasketImpl;
import ru.otus.homework.atm.atmstructure.BankNoteDispenser;
import ru.otus.homework.atm.cash.BankNote;
import ru.otus.homework.atm.cash.BankNoteImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ATMTest {
    @Mock
    BankNoteDispenser<BankNote> bankNoteDispenser;
    @Mock
    BankNote bankNote5000;
    @Mock
    BankNote bankNote500;

    ATM atm;

    @Test
    void giveCash() {
        atm = new ATMImpl(bankNoteDispenser);
    }

    @Test
    void getRemainCash() {
        Mockito.when(bankNoteDispenser.getRemainCash()).thenAnswer(invocation -> )
    }

    @Test
    void testNullConstructorArgument() {
        assertThrows(IllegalArgumentException.class, () -> new ATMImpl(null));

    }

    @Test
    void mainATMTest() {

    }

    Map<BankNote, Integer> getTestCashMap(){
        Map<BankNote, Integer> remainCash = new HashMap<>();
        remainCash.put()
    }
}