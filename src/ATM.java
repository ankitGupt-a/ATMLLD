import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

public class ATM {
    private final String atmId;
    private final String location;
    private final BankingService bankingService;
    private final CashDispenser cashDispenser;
    private boolean isCardInserted = false;
    private Card cardPresent;

    public static final AtomicLong transactionCounter = new AtomicLong(0);

    public ATM(final String atmId, final String location, final BankingService bankingService, final CashDispenser cashDispenser) {
        this.atmId = atmId;
        this.location = location;
        this.bankingService = bankingService;
        this.cashDispenser = cashDispenser;
    }

    public void insertCard(final Card card) {
        if (isCardInserted) {
            throw new IllegalStateException("Card is already inserted");
        }

        System.out.println("Card " + card.getCardNumber() + " is inserted");
        isCardInserted = true;
        cardPresent = card;
    }

    public void authenticateUser(final String pin) {
        if (!bankingService.authenticateUser(cardPresent, pin)) {
            System.out.println("Invalid password");
            ejectCard();
        }
    }
    public void withdrawalCash(final int amount) {
        final Account account = bankingService.getCardAccount(cardPresent);

        if (account != null && account.getBalance() < amount) {
            System.out.println("Insufficient balance in the account");
            ejectCard();
            return;
        }

        final Transaction transaction = new WithdrawlTransaction(amount, account);
        final String transactionId = bankingService.processTransaction(transaction);
        cashDispenser.dispenseCash(amount);
        System.out.println("Please take your receipt: \n" + printReceipt(transactionId, amount, account.getBalance()));
        ejectCard();
    }

    public void depositCash(final int amount) {
        final Account account = bankingService.getCardAccount(cardPresent);

        final Transaction transaction = new DepositTransaction(amount, account);
        final String transactionId = bankingService.processTransaction(transaction);
        System.out.println("Please take your receipt: \n" + printReceipt(transactionId, amount, account.getBalance()));
        ejectCard();
    }

    public void checkBalance() {
        final Account account = bankingService.getCardAccount(cardPresent);
        System.out.println(account.getBalance());
    }


    public void ejectCard() {
        isCardInserted = false;
        cardPresent = null;
        System.out.println("Card ejected, please take your card");
    }

    private String printReceipt(final String transactionId, final int amount, final int cashAvailable) {
        final long transactionNumber = transactionCounter.incrementAndGet();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "Time: " + timestamp + "\nATM transactionId: " + transactionNumber + "\namount/deposit withdrawal: " + amount + "\nPayment transactionId: " + transactionId
                + "\nCash available: " + cashAvailable;
    }
}
