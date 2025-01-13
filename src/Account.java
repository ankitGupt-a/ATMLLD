public class Account {
    private final String accountNumber;
    private int balance;

    public Account(final String accountNumber, final int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void withdrawalCash(final double amount) {
        balance -= amount;
    }

    public void depositCash(final double amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}
