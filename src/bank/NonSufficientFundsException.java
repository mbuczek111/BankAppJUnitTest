package bank;

import java.math.BigDecimal;

public class NonSufficientFundsException extends RuntimeException{
    public NonSufficientFundsException(BigDecimal balance)
    {
        System.out.println("Your current balance is: "+ balance + ". You cannot perform this operation.");
    }
}
