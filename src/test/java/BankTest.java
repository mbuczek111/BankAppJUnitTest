import bank.AccountNotFoundException;
import bank.Bank;
import bank.BankNotFoundException;
import bank.NationalBank;
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
        // some comment
    }
}
