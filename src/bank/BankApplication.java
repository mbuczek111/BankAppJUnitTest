package bank;

import bank.account.Account;
import bank.account.CreditAccount;
import bank.account.DepositAccount;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankApplication {
    private static final String ALIOR_BANK_NAME = "Alior Bank";
    private static final String MBANK_BANK_NAME = "mBank";

    public static void main(String[] args) {
        init();
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

        try {
            System.out.println(nb.getByName("Alior"));
        }
        catch(BankNotFoundException e) {
            System.out.println(e.getClass());
        }
        try {
            aliorDeposit.transferMoney("Alior Bank", 4, BigDecimal.valueOf(0));
        }
        catch(AccountNotFoundException e) {
            System.out.println(e.getClass());
        }
        try {
            aliorDeposit.withDraw(BigDecimal.valueOf(1000));
        }
        catch(NonSufficientFundsException e) {
            System.out.println(e.getClass());
        }

            System.out.println(nb.getByName("Alior Bank"));
            aliorDeposit.topUp(BigDecimal.valueOf(10000));
            aliorDeposit.withDraw(BigDecimal.valueOf(3000));
            System.out.println(nb.getByName("Alior Bank"));
            aliorDeposit.transferMoney("Alior Bank", 2, BigDecimal.valueOf(2000));
            System.out.println(nb.getByName("Alior Bank"));
        try {
            aliorCredit.withDraw(BigDecimal.valueOf(4000));
        }
        catch(ReachedCreditLimitException e)
        {
            System.out.println(e.getClass());
        }
            aliorCredit.withDraw(BigDecimal.valueOf(2500));
            System.out.println(nb.getByName("Alior Bank"));
            aliorCredit.applyPercentage();
            aliorDeposit.applyPercentage();
            System.out.println(nb.getByName("Alior Bank"));
            aliorDeposit.transferMoney("mBank",3,BigDecimal.valueOf(1000));
            System.out.println(nb.getByName("mBank"));

        System.out.println(aliorDeposit.getTransactionHistory());
        System.out.println(aliorCredit.getTransactionHistory());

    }

}
