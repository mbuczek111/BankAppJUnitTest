package bank;

import java.math.BigDecimal;

public class NonSufficientFundsException extends RuntimeException{
    public NonSufficientFundsException(BigDecimal balance)
    {
        super("Your current balance is: "+ balance + ". You cannot perform this operation.\n");
    }
}
