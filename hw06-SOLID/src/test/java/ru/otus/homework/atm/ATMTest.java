package ru.otus.homework.atm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.atm.atmstructure.BankNoteDispenser;
import ru.otus.homework.atm.cash.BankNote;
import ru.otus.homework.atm.cash.banknotemeta.BanknotesNominalEnum;
import ru.otus.homework.atm.exceptions.IncorrectSumOrNominalException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void giveCashTestCheckSum() {
        Mockito.when(bankNoteDispenser.getCashNominals()).then(invocation -> List.of(bankNote5000, bankNote500));
        atm = new ATMImpl(bankNoteDispenser);

        assertThrows(IncorrectSumOrNominalException.class, () -> atm.giveCash(50));
    }

    @Test
    void giveCashTestCheckNominal() {
        Mockito.when(bankNoteDispenser.getCashNominals()).then(invocation -> List.of(bankNote5000, bankNote500));
        Mockito.when(bankNoteDispenser.getRemainCash()).thenAnswer(invocation -> getTestCashMap());
        atm = new ATMImpl(bankNoteDispenser);

        assertThrows(IncorrectSumOrNominalException.class, () -> atm.giveCash(110));
        assertThrows(IncorrectSumOrNominalException.class, () -> atm.giveCash(7));

        Stream.of(BanknotesNominalEnum.values())
                .filter(banknotesNominalEnum -> (
                        BanknotesNominalEnum.BANK_NOTE_NOMINAL_5000.getValue() != banknotesNominalEnum.getValue()
                                && BanknotesNominalEnum.BANK_NOTE_NOMINAL_500.getValue() != banknotesNominalEnum.getValue()
                                && BanknotesNominalEnum.BANK_NOTE_NOMINAL_1000.getValue() != banknotesNominalEnum.getValue()
                                && BanknotesNominalEnum.BANK_NOTE_NOMINAL_2000.getValue() != banknotesNominalEnum.getValue()))
                .forEach(banknotesNominalEnum -> assertThrows(IncorrectSumOrNominalException.class, () -> atm.giveCash(banknotesNominalEnum.getValue().value())));
    }

    @Test
    void getRemainCash() {
        atm = new ATMImpl(bankNoteDispenser);
        assertEquals(0, atm.getRemainCash());
        Mockito.when(bankNoteDispenser.getRemainCash()).thenAnswer(invocation -> getTestCashMap());
        assertEquals(300000, atm.getRemainCash());
    }

    @Test
    void testNullConstructorArgument() {
        assertThrows(IllegalArgumentException.class, () -> new ATMImpl(null));
    }

    Map<BankNote, Integer> getTestCashMap() {
        Map<BankNote, Integer> remainCash = new HashMap<>();
        remainCash.put(bankNote500, 100);
        remainCash.put(bankNote5000, 50);

        Mockito.when(bankNote500.getBanknoteNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_500.getValue().value());
        Mockito.when(bankNote5000.getBanknoteNominal()).then(invocation -> BanknotesNominalEnum.BANK_NOTE_NOMINAL_5000.getValue().value());

        return remainCash;
    }
}