import java.util.UUID;

public abstract class Transaction {
   protected final int amount;
   protected final Account account;
   protected final String transactionId;

   public Transaction(final int amount, final Account account) {
       this.amount = amount;
       this.account = account;
       this.transactionId = UUID.randomUUID().toString();
   }

   public abstract String executeTransaction();
}
