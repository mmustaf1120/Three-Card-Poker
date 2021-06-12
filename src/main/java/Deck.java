import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**\
 * Deck represents a deck of cards. It contains 52 unique cards.
 */
public class Deck extends ArrayList<Card> {
    public List<Character> suits = Arrays.asList('C', 'D', 'S', 'H');

    Deck() {
        createNewDeck();
    }

    public void newDeck() {
        this.clear();
        this.createNewDeck();
    }

    private void createNewDeck() {
        for (Character s : suits) {
            for (int i = 2; i <= 14; i++) {
                this.add(new Card(s, i));
            }
        }
        Collections.shuffle(this);

    }
    
    public static final long serialVersionUID = 1L;
}