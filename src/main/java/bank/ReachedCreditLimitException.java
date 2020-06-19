package bank;

import java.math.BigDecimal;

public class ReachedCreditLimitException extends RuntimeException{
    public ReachedCreditLimitException(BigDecimal balance, BigDecimal limit)
    {
        super("Your current credit limit is: "+limit +" Your current balance is: "+ balance +
                ". You cannot perform this operation.\n");
    }
}
