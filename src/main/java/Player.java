import java.util.ArrayList;

/**
 * Player represnts a player on the board. It is responsible for managing types of bets
 * the player play. It also holds the total winnings
 */
public class Player {
    ArrayList<Card> hand;
    int anteBet;
    int playBet;
    int pairPlusBet;
    int totalWinnings;

    Player() {
    	 hand = new ArrayList<Card>();
         anteBet = 5;
         playBet = 0;
         pairPlusBet = 0;
         totalWinnings = 0;
    }

    // Ante bet
    public void loseAnteBet() {
        this.anteBet = 5;
    }
    public void losePlayBet() {
        this.playBet = 0;
    }
    public void setAnteBet(int anteBet) {
        if (anteBet >= 5 && anteBet <= 25)
            this.anteBet = anteBet;
    }
    public void increaseAnteBet() {
        setAnteBet(anteBet + 1);
    }
    public void decreaseAnteBet() {
        setAnteBet(anteBet - 1);
    }

    // Pair plus bet
    public void setPairPlusBet(int pairPlusBet) {
        if (pairPlusBet == 0 || (pairPlusBet >= 5 && pairPlusBet <= 25))
            this.pairPlusBet = pairPlusBet;
    }
    public void increasePairPlusBet() {
        if (pairPlusBet == 0) {
            setPairPlusBet(5);
        } else {
            setPairPlusBet(pairPlusBet + 1);
        }
    }
    public void decreasePairPlusBet() {
        if (pairPlusBet == 5) {
            setPairPlusBet(0);
        } else {
            setPairPlusBet(pairPlusBet - 1);
        }
    }
    public void losePairPlusBet() {
        this.anteBet = 0;
    }

    // Player bet
    public void setPlayBet() {
        this.playBet = this.anteBet;
    }

    // Total winnings
    public void setTotalWinnings(int winnings) {
        this.totalWinnings += winnings;
    }

    public void reset(){
        this.pairPlusBet = 0;
        this.anteBet= 5;
        this.playBet = 0;
        if(this.hand.size() == 3) {
            this.hand.clear();
        }
    }

    public void resetTotalWinning(){
        this.totalWinnings = 0;
    }

}
