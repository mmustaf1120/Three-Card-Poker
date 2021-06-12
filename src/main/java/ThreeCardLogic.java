import java.util.ArrayList;
import java.util.Comparator;

/**
 * ThreeCardLogic holds the logic of how cards are evaluated and
 * compared as per the three card poker rule
 */
public class ThreeCardLogic {
    public static int evalHand(ArrayList<Card> hand) {
        // Clone so as not to mutate the passed arrays, and sort them for easier comparing
        ArrayList<Card> sortedhand = new ArrayList<Card>(hand);
        sortedhand.sort(Comparator.comparingInt((Card c) -> c.value));

        final Card firstCard = sortedhand.get(0);
        final Card secondCard = sortedhand.get(1);
        final Card thirdCard = sortedhand.get(2);

        // Straight flush
        if (firstCard.suit == secondCard.suit && secondCard.suit == thirdCard.suit
                && firstCard.value == secondCard.value - 1 && firstCard.value == thirdCard.value - 2
        ) {
            return 1;
        }

        // Three of a kind
        if (firstCard.value == secondCard.value && secondCard.value == thirdCard.value) {
            return 2;
        }

        // Straight
        if (firstCard.value == secondCard.value - 1 && firstCard.value == thirdCard.value - 2) {
            return 3;
        }

        // Flush
        if (firstCard.suit == secondCard.suit && secondCard.suit == thirdCard.suit) {
            return 4;
        }

        // Pair
        if (firstCard.value == secondCard.value || firstCard.value == thirdCard.value
                || secondCard.value == thirdCard.value) {
            return 5;
        }

        // If nothing matches, it means it's just a high card
        return 0;
    }


    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        // By default it's a high card multiplier
        int multiplier = 0;

        ArrayList<Card> sortedHand = new ArrayList<Card>(hand);
        sortedHand.sort(Comparator.comparingInt((Card c) -> c.value));

        switch (evalHand(hand)) {
            // Straight Flush
            case 1:
                multiplier = 40;
                break;

            // Three of a kind
            case 2:
                multiplier = 30;
                break;

            // Straight
            case 3:
                multiplier = 6;
                break;

            // Flush
            case 4:
                multiplier = 3;
                break;

            // Pair
            case 5:
                multiplier = 1;
                break;
        }

        return multiplier * bet;
    }

    // Returns 1 when dealer wins, 2 when player wins and 0 when draw
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        // Clone so as not to mutate the passed arrays, and sort them for easier comparing
        ArrayList<Card> sortedDealer = new ArrayList<Card>(dealer);
        sortedDealer.sort(Comparator.comparingInt((Card c) -> c.value));
        ArrayList<Card> sortedPlayer = new ArrayList<Card>(player);
        sortedPlayer.sort(Comparator.comparingInt((Card c) -> c.value));


        // If dealer hand is just high and player's is better, player wins
        if (evalHand(dealer) == 0 && evalHand(player) > 0) {
            return 2;
        }

        // If player hand is just high and dealer's is better, dealer wins
        if (evalHand(player) == 0 && evalHand(dealer) > 0) {
            return 1;
        }

        // If they are both of same kind, highest value is checked
        if (evalHand(dealer) == evalHand(player)) {
            // If it's a pair
            if (evalHand(dealer) == 5) {
                // Finding out which card is pair
                Card pairDealerCard;
                Card nonPairDealerCard;
                if (dealer.get(0).value == dealer.get(1).value) {
                    pairDealerCard = dealer.get(0);
                    nonPairDealerCard = dealer.get(2);
                } else if (dealer.get(0).value == dealer.get(2).value) {
                    pairDealerCard = dealer.get(0);
                    nonPairDealerCard = dealer.get(1);
                } else {
                    //this will always be when 1 and 2 are a pair
                    pairDealerCard = dealer.get(1);
                    nonPairDealerCard = dealer.get(0);
                }

                // Finding out which card is pair
                Card pairPlayerCard;
                Card nonPairPlayerCard;
                if (player.get(0).value == player.get(1).value) {
                    pairPlayerCard = player.get(0);
                    nonPairPlayerCard = player.get(2);
                } else if (player.get(0).value == player.get(2).value) {
                    pairPlayerCard = player.get(0);
                    nonPairPlayerCard = player.get(1);
                } else {
                    //this will always be when 1 and 2 are a pair
                    pairPlayerCard = player.get(1);
                    nonPairPlayerCard = player.get(0);
                }

                // First check one of pair cards
                if (pairDealerCard.value > pairPlayerCard.value) {
                    return 1;
                } else if (pairDealerCard.value < pairPlayerCard.value) {
                    return 2;
                } else {
                    // Check the non Pair cards
                    if (nonPairDealerCard.value > nonPairPlayerCard.value) {
                        return 1;
                    } else if (nonPairDealerCard.value < nonPairPlayerCard.value) {
                        return 2;
                    } else {
                        return 0;
                    }
                }
            }


            // For anything except pairs
            if (sortedDealer.get(2).value > sortedPlayer.get(2).value) {
                return 1;
            } else if (sortedDealer.get(2).value < sortedPlayer.get(2).value) {
                return 2;
            } else {
                // If highest value is same, check the second highest card
                if (sortedDealer.get(1).value > sortedPlayer.get(1).value) {
                    return 1;
                } else if (sortedDealer.get(1).value < sortedPlayer.get(1).value) {
                    return 2;
                } else {
                    // If the second highest value is same, check the smallest card
                    if (sortedDealer.get(0).value > sortedPlayer.get(0).value) {
                        return 1;
                    } else if (sortedDealer.get(0).value < sortedPlayer.get(0).value) {
                        return 2;
                    } else {
                        // If they are same, it's a draw
                        return 0;
                    }
                }
            }
        }
        // Less number wins since ZERO is already checked at this point
        else if (evalHand(dealer) < evalHand(player))  {
            return 1;
        } else {
            return 2;
        }
    }

}
