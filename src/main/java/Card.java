/**
 * Card represents a card. For example, Ace of Spade, Two of Hearts.
 */
public class Card {
    char suit;
    int value;

    Card(char suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    String getName() {
        return "" + suit + value;
    }

}