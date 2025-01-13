import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BankingService {

    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final Map<String, String> cardAccountMapping = new ConcurrentHashMap<>();
    public String createAccount(final int initialBalance) {
        final String accountNumber = UUID.randomUUID().toString();
        accounts.put(accountNumber, new Account(accountNumber, initialBalance));
        return accountNumber;
    }

    public void addCardToAccount(final String accountNumber, final Card card) {
        cardAccountMapping.put(card.getCardNumber(), accountNumber);
    }

    public Account getCardAccount(final Card card) {
        final String accountNumber = cardAccountMapping.get(card.getCardNumber());
        return getAccount(accountNumber);
    }
    private Account getAccount(final String accountNumber) {
        return accounts.get(accountNumber);
    }

    public String processTransaction(final Transaction transaction) {
        return transaction.executeTransaction();
    }

    public boolean authenticateUser(final Card cardPresent, final String pin) {
        return cardPresent.getPin().equals(pin);
    }
}
