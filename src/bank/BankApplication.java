package bank;

import bank.account.Account;
import bank.account.CreditAccount;
import bank.account.DepositAccount;
import java.math.BigDecimal;


public class BankApplication {
    private static final String ALIOR_BANK_NAME = "Alior Bank";
    private static final String MBANK_BANK_NAME = "mBank";

    public static void main(String[] args) {

        init();
        NationalBank nb = NationalBank.getInstance();

        try {
            System.out.println(nb.getByName("Alior"));
        }
        catch(BankNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            nb.getByName(ALIOR_BANK_NAME).getByNumber(1).transferMoney("Alior Bank", 4, BigDecimal.valueOf(0));
        }
        catch(AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            nb.getByName(ALIOR_BANK_NAME).getByNumber(1).withDraw(BigDecimal.valueOf(1000));
        }
        catch(NonSufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(nb.getByName("Alior Bank"));
        nb.getByName(ALIOR_BANK_NAME).getByNumber(1).topUp(BigDecimal.valueOf(10000));
        nb.getByName(ALIOR_BANK_NAME).getByNumber(1).withDraw(BigDecimal.valueOf(3000));
        System.out.println(nb.getByName("Alior Bank"));
        nb.getByName(ALIOR_BANK_NAME).getByNumber(1).transferMoney("Alior Bank", 2, BigDecimal.valueOf(2000));
        System.out.println(nb.getByName("Alior Bank"));
        try {
            nb.getByName(ALIOR_BANK_NAME).getByNumber(2).withDraw(BigDecimal.valueOf(4000));
        }
        catch(ReachedCreditLimitException e)
        {
            System.out.println(e.getMessage());
        }
        nb.getByName(ALIOR_BANK_NAME).getByNumber(2).withDraw(BigDecimal.valueOf(2500));
        System.out.println(nb.getByName("Alior Bank"));
        nb.getByName(ALIOR_BANK_NAME).getByNumber(2).applyPercentage();
        nb.getByName(ALIOR_BANK_NAME).getByNumber(1).applyPercentage();
        System.out.println(nb.getByName("Alior Bank"));
        nb.getByName(ALIOR_BANK_NAME).getByNumber(1).transferMoney("mBank",3,BigDecimal.valueOf(1000));
        System.out.println(nb.getByName("mBank"));

        System.out.println(nb.getByName(ALIOR_BANK_NAME).getByNumber(1).getTransactionHistory());
        System.out.println(nb.getByName(ALIOR_BANK_NAME).getByNumber(2).getTransactionHistory());

    }

    private static void init() {
        NationalBank nb = NationalBank.getInstance();

        Bank alior = new Bank(ALIOR_BANK_NAME);
        Bank mbank = new Bank(MBANK_BANK_NAME);

        nb.registerBank(alior);
        nb.registerBank(mbank);

        Account aliorDeposit = new DepositAccount(BigDecimal.valueOf(0.05));
        Account aliorCredit = new CreditAccount(BigDecimal.valueOf(0.1), BigDecimal.valueOf(-1_000));
        alior.addAccount(aliorCredit);
        alior.addAccount(aliorDeposit);

        Account mbankDeposit = new DepositAccount(BigDecimal.valueOf(0.45));
        Account mbankCredit = new CreditAccount(BigDecimal.valueOf(6), BigDecimal.valueOf(5_000));
        mbank.addAccount(mbankCredit);
        mbank.addAccount(mbankDeposit);

    }

}
