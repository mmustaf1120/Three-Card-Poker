import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DeckTest {
    @Test
    public void testIfDeckSize52() {
        // Test if the size of the deck is correct
        Deck deck = new Deck();
        assert deck.size() == 52;
    }

    @Test
    public void testIfNewDeckCreated() {
        // test if new deck is shuffled well
        Deck deck = new Deck();
        Deck tempDeck = (Deck) deck.clone();
        deck.newDeck();
        int i;
        int match = 0;
        for (i = 0; i < 52; i++) {
            if (deck.get(i) == tempDeck.get(i)) {
                match += 1;
            }
        }
        assert match != 52;
    }

    @Test
    public void testIfAll52CardUnique() {
        // Test if all 52 cards are unique
        ArrayList<String> tempDeck = new ArrayList<String>();
        Deck deck = new Deck();
        int i;
        for (i = 0; i < 52; i++) {
            if (i == 0) {
                tempDeck.add(deck.get(i).suit + String.valueOf(deck.get(i).value));
            } else {
                if (tempDeck.contains(deck.get(i).suit + String.valueOf(deck.get(i).value))) {
                    assert false;
                } else {
                    tempDeck.add(deck.get(i).suit + String.valueOf(deck.get(i).value));
                }
            }
        }
    }

    @Test
    public void testIfNewDeckCreatedIsValid() {
        // Test if the new created deck doesn't have duplicated cards
        Deck deck = new Deck();
        ArrayList<String> tempDeck = new ArrayList<String>();
        deck.newDeck();
        int i;
        for (i = 0; i < 52; i++) {
            if (i == 0) {
                tempDeck.add(deck.get(i).suit + String.valueOf(deck.get(i).value));
            } else {
                if (tempDeck.contains(deck.get(i).suit + String.valueOf(deck.get(i).value))) {
                    assert false;
                } else {
                    tempDeck.add(deck.get(i).suit + String.valueOf(deck.get(i).value));
                }
            }
        }
    }

    @Test
    public void testIfDeckHasValidCards() {
        // Test if the deck has valid cards
        Deck deck = new Deck();
        int i;
        for (i = 0; i < 52; i++) {
            // Check the suits
            if (!(deck.get(i).suit == 'S' || deck.get(i).suit == 'H' || deck.get(i).suit == 'D' || deck.get(i).suit == 'C')) {
                assert false;
            }
            // Check the bounds
            if (!(deck.get(i).value >= 2 && deck.get(i).value <= 14)) {
                assert false;
            }
        }
        assert true;
    }
}
