package ru.otus.homework.atm.cash.banknotemeta;

/**
 * номиналы купюр
 */
public enum BanknotesNominalEnum {
    BANK_NOTE_NOMINAL_5000(5000), BANK_NOTE_NOMINAL_2000(2000),
    BANK_NOTE_NOMINAL_1000(1000), BANK_NOTE_NOMINAL_200(200),
    BANK_NOTE_NOMINAL_500(500), BANK_NOTE_NOMINAL_100(100),
    BANK_NOTE_NOMINAL_50(50);

    private int value;

    BanknotesNominalEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
