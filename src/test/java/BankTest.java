import bank.AccountNotFoundException;
import bank.Bank;
import bank.BankNotFoundException;
import bank.NationalBank;
import bank.account.Account;
import bank.account.CreditAccount;
import bank.account.DepositAccount;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    @Test
    public void getByName_whenNotFound_throwsBankNotFoundException_withMessageBankNotFound() {
        BankNotFoundException exception = assertThrows(BankNotFoundException.class, () -> NationalBank.getInstance().getByName("Alior"));
        assertThat(exception.getMessage()).contains("Bank not found");
    }

    @Test
    public void getByNumber_whenNotFound_throwsAccountNotFoundException_withMessageAccountNotFound() {
        NationalBank nb = NationalBank.getInstance();
        Bank alior = new Bank("Alior Bank");
        nb.registerBank(alior);
        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class, () -> nb.getByName("Alior Bank").getByNumber(1).transferMoney("Alior Bank", 4, BigDecimal.valueOf(0)));
        assertThat(exception.getMessage()).contains("Account not found");
    }
    @Test
    public void topUp_withAmountOfMoney_adds10000toAccountsBalance() {
        NationalBank nb = NationalBank.getInstance();
        Bank alior = new Bank("Alior Bank");
        nb.registerBank(alior);
        Account aliorCredit = new CreditAccount(BigDecimal.valueOf(0.1), BigDecimal.valueOf(-1000));
        alior.addAccount(aliorCredit);
        nb.getByName("Alior Bank").getByNumber(1).topUp(BigDecimal.valueOf(10000));
        assertThat(aliorCredit.getBalance().toString()).contains("10000");
    }
    @Test
    public void addAccount_incrementsAccountNumberByOne()
    {
        NationalBank nb = NationalBank.getInstance();
        Bank alior = new Bank("Alior Bank");
        Account aliorDeposit = new DepositAccount(BigDecimal.valueOf(0.05));

        alior.addAccount(aliorDeposit);

        assertThat(aliorDeposit.getAccountNumber()).isEqualTo(2);
    }
}
