package ru.otus.homework.atm.cash.banknotemeta;

/**
 * номиналы купюр
 */
public enum BanknotesNominalEnum {
    BANK_NOTE_NOMINAL_5000(new BanknoteNominal(5000)), BANK_NOTE_NOMINAL_2000(new BanknoteNominal(2000)),
    BANK_NOTE_NOMINAL_1000(new BanknoteNominal(1000)), BANK_NOTE_NOMINAL_200(new BanknoteNominal(200)),
    BANK_NOTE_NOMINAL_500(new BanknoteNominal(500)), BANK_NOTE_NOMINAL_100(new BanknoteNominal(100)),
    BANK_NOTE_NOMINAL_50(new BanknoteNominal(50));

    private BanknoteNominal value;

    BanknotesNominalEnum(BanknoteNominal value) {
        this.value = value;
    }

    public BanknoteNominal getValue() {
        return value;
    }
}
