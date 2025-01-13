public class Card {
    private final String cardNumber;
    private String pin;

    public Card(final String cardNumber, final String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }
}
