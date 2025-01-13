public class CashDispenser {
    private int cashAvailable;

    public CashDispenser(final int initialCash) {
        this.cashAvailable = initialCash;
    }

    public synchronized void addMoreCash(final int amount) {
        cashAvailable = amount;
    }

    public synchronized void dispenseCash(final int amount) {
        if (amount>cashAvailable) {
            throw new RuntimeException("Amount " + amount + " is not available, select different amount");
        }

        cashAvailable -= amount;
        System.out.println("Cash dispensed: " + amount);
    }
}
