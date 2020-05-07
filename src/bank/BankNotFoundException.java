package bank;

public class BankNotFoundException extends RuntimeException{
    public BankNotFoundException(String name)
    {
        super("Bank not found: " + name +". ");
    }
}
