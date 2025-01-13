public class DepositTransaction extends Transaction{

    public DepositTransaction(final int amount, final Account account) {
        super(amount, account);
    }

    public String executeTransaction() {
        account.depositCash(amount);
        return transactionId;
    }
}
