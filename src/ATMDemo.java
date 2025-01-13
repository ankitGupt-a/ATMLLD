
public class ATMDemo {
    public static void main(String[] args) {
        final BankingService bankingService = new BankingService();
        final CashDispenser cashDispenser = new CashDispenser(5000);
        final ATM atm = new ATM("ATM1", "Pune", bankingService, cashDispenser);

        final Card card = new Card("1234", "1234");
        final String accountNumber = bankingService.createAccount(1000);
        bankingService.addCardToAccount(accountNumber, card);

        try {
            atm.insertCard(card);
            atm.authenticateUser("1234");
            atm.checkBalance();
            atm.withdrawalCash(500);

            atm.insertCard(card);
            atm.authenticateUser("1234");
            atm.checkBalance();
            atm.withdrawalCash(1000);

            atm.insertCard(card);
            atm.authenticateUser("1234");
            atm.checkBalance();
            atm.depositCash(1000);

            atm.insertCard(card);
            atm.authenticateUser("1234");
            atm.checkBalance();
            atm.withdrawalCash(1000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}