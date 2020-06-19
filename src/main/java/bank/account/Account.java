package bank.account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Account {
    private int accountNumber;
    private BigDecimal balance;
    private BigDecimal percents;

    private List<String> transactionHistory;

    private static int lastAccountNumber;

    public Account(BigDecimal percents) {
        balance = BigDecimal.ZERO;
        //lastAccountNumber++;
        accountNumber = ++lastAccountNumber;
        this.percents = percents;
        transactionHistory = new ArrayList<>();
    }

    public BigDecimal topUp(BigDecimal amount) {
        balance = balance.add(amount);
        this.addTransactionLog("Top up", LocalDateTime.now());
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPercents() {
        return percents;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    protected void addTransactionLog(String logMessage, LocalDateTime time) {
        String timet = time.toString();
        String bal = getBalance().toString();
        transactionHistory.add(logMessage+ ", balance now " + bal+ " "+ timet);

    }

    public List<String> getTransactionHistory() {
        List<String> transHistCopy = new ArrayList<>(transactionHistory);

        return transHistCopy;
    }

    public abstract BigDecimal withDraw(BigDecimal amount);

    public abstract BigDecimal applyPercentage();

    public abstract BigDecimal transferMoney(String bankName, int accountNumber, BigDecimal amount);

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNumber == account.accountNumber &&
                Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance);
    }
}
