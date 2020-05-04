package bank.account;

import bank.NationalBank;
import bank.ReachedCreditLimitException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreditAccount extends Account {
    private BigDecimal creditLimit;

    public CreditAccount(BigDecimal percents, BigDecimal creditLimit) {
        super(percents);
        this.creditLimit = creditLimit;
    }

    @Override
    public BigDecimal withDraw(BigDecimal amount) throws ReachedCreditLimitException {
        if(this.getBalance().subtract(amount).compareTo(creditLimit) > 0) {
            this.setBalance(this.getBalance().subtract(amount));
            this.addTransactionLog("Withdrawing from CreditAccount", LocalDateTime.now());

        }
        else
            throw new ReachedCreditLimitException(this.getBalance(),creditLimit);

        return null;
    }


    /**
     * For example current balance is -100 (minus 100)
     * percentage is 3%
     * after applying percentage current balance should be -103
     * <p>
     * For example current balance is +100 (plus 100)
     * percentage is 3%
     * after applying percentage current balance should be still +100
     */
    @Override
    public BigDecimal applyPercentage() {
        if(this.getBalance().compareTo(BigDecimal.ZERO) < 0)
        {
            this.setBalance(this.getBalance().multiply(getPercents().add(BigDecimal.ONE)));
            this.addTransactionLog("Applying percents on Credit Account", LocalDateTime.now());

        }
        return null;
    }

    @Override
    public BigDecimal transferMoney(String bankName, int accountNumber, BigDecimal amount) throws ReachedCreditLimitException{
        if(this.getBalance().subtract(amount).compareTo(creditLimit) > 0) {
            this.setBalance(this.getBalance().subtract(amount));
            NationalBank.getByName(bankName).getByNumber(accountNumber).setBalance(NationalBank.getByName(bankName).getByNumber(accountNumber).getBalance().add(amount));
            this.addTransactionLog("Transferring money from Credit Account", LocalDateTime.now());

        }
            else
            throw new ReachedCreditLimitException(this.getBalance(),creditLimit);
        return null;
    }
}
