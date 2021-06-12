import java.util.ArrayList;
import java.util.Comparator;

/**
 *  Dealer represents the dealer in the game. It is responsible for holding
 *  the dealer hand and also dealing cards to other players from the deck
 */
public class Dealer {
    Deck theDeck;
    ArrayList<Card> dealersHand;

    Dealer() {
        this.theDeck = new Deck();
    }
    public void resetHand(){
        this.dealHand().clear();
    }
    public void  resetDeck(){
        this.theDeck.newDeck();
    }
    public ArrayList<Card> dealHand() {
        ArrayList<Card> cards = new ArrayList<Card>();

        // If the deck is below 34, reshuffle the deck
        if (!(this.theDeck.size() > 34)) {
          theDeck.newDeck();
        }

        for (int i = 0; i < 3; i++) {
            if (this.theDeck.size() > 3)
                cards.add(this.theDeck.get(0));
            this.theDeck.remove(0);
        }

        return cards;
    }

    public boolean atLeastQueenHigh() {
        ArrayList<Card> sortedhand = new ArrayList<Card>(dealersHand);
        sortedhand.sort(Comparator.comparingInt((Card c) -> c.value));

        if (ThreeCardLogic.evalHand(dealersHand) > 0) {
            return true;
        }

        return sortedhand.get(2).value >= 12;
    }
}