import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    public void testDealerDeck() {
        // Assure that the dealer's deck size is correct
        Dealer dealer = new Dealer();
        assert dealer.theDeck.size() == 52;
    }

    @Test
    public void testDealHandRemove3Cards() {
        // Assure that exactly 3 cards are removed when dealt
        Dealer dealer = new Dealer();
        dealer.dealHand();
        assert dealer.theDeck.size() == 49;
    }

    @Test
    public void testDealerDeckResets34() {
        // Check that after dealing 7 hands the dealers deck would have reset
        Dealer dealer = new Dealer();
        int i;
        // Deal 7 hands
        for (i = 0; i < 7; i++) {
            dealer.dealHand();
        }
        assert dealer.theDeck.size() == 49;
    }

    @Test
    public void testDealerSetHand() {
        // Check that the dealt hand is of correct size
        Dealer dealer = new Dealer();
        dealer.dealersHand = dealer.dealHand();
        assert dealer.dealersHand.size() == 3;
    }

    @Test
    public void testDealerHandValidCards() {
        // Test that the cards dealt are valid
        Dealer dealer = new Dealer();
        dealer.dealersHand = dealer.dealHand();
        int i;

        for (i = 0; i < 3; i++) {
            // Check if the cards are of either of the four suits
            if (!(dealer.dealersHand.get(i).suit == 'S' || dealer.dealersHand.get(i).suit == 'H' || dealer.dealersHand.get(i).suit == 'D' || dealer.dealersHand.get(i).suit == 'C')) {
                assert false;
            }
            // Check if the card value is within thr bound
            if (!(dealer.dealersHand.get(i).value >= 2 && dealer.dealersHand.get(i).value <= 14)) {
                assert false;
            }
        }
    }


}
