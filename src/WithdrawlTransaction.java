public class WithdrawlTransaction extends Transaction{

    public WithdrawlTransaction(final int amount, final Account account) {
        super(amount, account);
    }

    public String executeTransaction() {
        account.withdrawalCash(amount);
        return transactionId;
    }
}
