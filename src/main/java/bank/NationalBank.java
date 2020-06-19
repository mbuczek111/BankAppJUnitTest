package bank;

import java.util.HashSet;
import java.util.Set;

public class NationalBank {
    private static Set<Bank> banks;

    private static NationalBank NATIONAL_BANK;

    private NationalBank() {
        banks = new HashSet<>();
    }

    public static NationalBank getInstance() {
        if (NATIONAL_BANK == null) {
            NATIONAL_BANK = new NationalBank();
        }
        return NATIONAL_BANK;
    }

    public static Bank getByName(String name) throws BankNotFoundException{

        for (Bank b:banks) {
            if (b.getName().equals(name))
                return b;
        }
        throw new BankNotFoundException(name);
    }

    public void registerBank(Bank bank) {
        banks.add(bank);
    }
}
