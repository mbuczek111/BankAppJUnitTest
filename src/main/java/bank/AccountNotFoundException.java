package bank;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(int number, String name)
    {
        super("Account not found: "+ number + " in "+name +".");
    }
}
