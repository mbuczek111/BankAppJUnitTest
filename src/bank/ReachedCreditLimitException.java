package bank;

import java.math.BigDecimal;

public class ReachedCreditLimitException extends RuntimeException{
    public ReachedCreditLimitException(BigDecimal balance, BigDecimal limit)
    {
        System.out.println("Your current credit limit is: "+limit);
        System.out.println("Your current balance is: "+ balance + ". You cannot perform this operation.");
    }
}
