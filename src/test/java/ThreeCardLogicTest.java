import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ThreeCardLogicTest {

    @Test
    void testStraightFlush() {
        // Test that the straight flush is evaluated correctly
        final ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
                new Card('C', 10),
                new Card('C',9),
                new Card('C', 8)
        ));
        assert ThreeCardLogic.evalHand(hand) == 1;
    }

    @Test
    void testThreeOfAKind() {
        // Test that three of a kind is evaluated correctly
        final ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
                new Card('C', 12),
                new Card('H',12),
                new Card('S', 12)
        ));
        assert ThreeCardLogic.evalHand(hand) == 2;
    }

    @Test
    void testStraight() {
        // Test that straight is evaluated correctly
        final ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
                new Card('D', 8),
                new Card('C',7),
                new Card('D', 6)
        ));
        assert ThreeCardLogic.evalHand(hand) == 3;
    }

    @Test
    void testFlush() {
        // Test that flush is evaluated correctly
        final ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
                new Card('D', 13),
                new Card('D',9),
                new Card('D', 7)
        ));
        assert ThreeCardLogic.evalHand(hand) == 4;
    }

    @Test
    void testPair() {
        // Test that pair is evaluated correctly
        final ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
                new Card('H', 13),
                new Card('C',13),
                new Card('D', 9)
        ));
        assert ThreeCardLogic.evalHand(hand) == 5;
    }

    @Test
    void testHigh() {
        // Test that high card is evaluated correctly
        final ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
                new Card('H', 13),
                new Card('C',6),
                new Card('D', 9)
        ));
        assert ThreeCardLogic.evalHand(hand) == 0;
    }

    @Test
    void StraightFlushVSThreeOfAKind() {
        // Test straight flush wins three of a kind
        // Dealer wins
        final ArrayList<Card> dealerStraightFlush = new ArrayList<Card>(Arrays.asList(
                new Card('C', 10),
                new Card('C',9),
                new Card('C', 8)
        ));
        final ArrayList<Card> playerThreeOfAKind = new ArrayList<Card>(Arrays.asList(
                new Card('C', 12),
                new Card('H',12),
                new Card('S', 12)
        ));
        assert ThreeCardLogic.compareHands(dealerStraightFlush, playerThreeOfAKind) == 1;
    }

    @Test
    void straightWinsFlush() {
        // Test straight wins flush
        final ArrayList<Card> playerStraight = new ArrayList<Card>(Arrays.asList(
                new Card('D', 8),
                new Card('C',7),
                new Card('D', 6)
        ));
        final ArrayList<Card> dealerFlush = new ArrayList<Card>(Arrays.asList(
                new Card('D', 13),
                new Card('D',9),
                new Card('D', 7)
        ));
        assert ThreeCardLogic.compareHands(dealerFlush, playerStraight) == 2;
    }

    @Test
    void testDraw() {
        // Test Straight draws straight
        final ArrayList<Card> playerStraight = new ArrayList<Card>(Arrays.asList(
                new Card('D', 8),
                new Card('C',7),
                new Card('D', 6)
        ));
        final ArrayList<Card> dealerFlush = new ArrayList<Card>(Arrays.asList(
                new Card('D', 8),
                new Card('H',7),
                new Card('C', 6)
        ));
        assert ThreeCardLogic.compareHands(dealerFlush, playerStraight) == 0;
    }

    @Test
    void testFlushDW() {
        // Test flush with higher card wins flush with lower card
        // Dealer Wins
        final ArrayList<Card> dealer = new ArrayList<Card>(Arrays.asList(
                new Card('D', 10),
                new Card('D',7),
                new Card('D', 13)
        ));
        final ArrayList<Card> player = new ArrayList<Card>(Arrays.asList(
                new Card('H', 13),
                new Card('H',9),
                new Card('H', 4)
        ));
        assert ThreeCardLogic.compareHands(dealer, player) == 1;

    }

    @Test
    void testFlushPW() {
        // Test flush with higher card wins flush with lower card
        // Player wins
        final ArrayList<Card> dealer = new ArrayList<Card>(Arrays.asList(
                new Card('D', 10),
                new Card('D',7),
                new Card('D', 13)
        ));
        final ArrayList<Card> player = new ArrayList<Card>(Arrays.asList(
                new Card('H', 13),
                new Card('H',10),
                new Card('H', 9)
        ));
        assert ThreeCardLogic.compareHands(dealer, player) == 2;
    }

    @Test
    void testStraightPW() {
        // Test straight with higher card wins straight with lower card
        // Player wins
        final ArrayList<Card> dealer = new ArrayList<Card>(Arrays.asList(
                new Card('D', 10),
                new Card('H',11),
                new Card('S', 12)
        ));
        final ArrayList<Card> player = new ArrayList<Card>(Arrays.asList(
                new Card('D', 12),
                new Card('S',14),
                new Card('H', 13)
        ));
        assert ThreeCardLogic.compareHands(dealer, player) == 2;
    }

    @Test
    void testPairDifferentDW() {
        // // Test pair with higher card wins pair with lower card
        // Dealer wins
        final ArrayList<Card> dealer = new ArrayList<Card>(Arrays.asList(
                new Card('D', 12),
                new Card('H',9),
                new Card('S', 12)
        ));
        final ArrayList<Card> player = new ArrayList<Card>(Arrays.asList(
                new Card('D', 11),
                new Card('S',11),
                new Card('H', 14)
        ));
        assert ThreeCardLogic.compareHands(dealer, player) == 1;
    }

    @Test
    void testPairDifferentPW() {
        // Test pair with higher card wins flush with lower card
        // Player wins
        final ArrayList<Card> dealer = new ArrayList<Card>(Arrays.asList(
                new Card('D', 12),
                new Card('H',14),
                new Card('S', 12)
        ));
        final ArrayList<Card> player = new ArrayList<Card>(Arrays.asList(
                new Card('D', 13),
                new Card('S',13),
                new Card('H', 9)
        ));
        assert ThreeCardLogic.compareHands(dealer, player) == 2;
    }

    @Test
    void testPairSameDW() {
        // Test pair with higher card wins flush with lower card
        // Player wins
        final ArrayList<Card> dealer = new ArrayList<Card>(Arrays.asList(
                new Card('D', 12),
                new Card('H',13),
                new Card('S', 12)
        ));
        final ArrayList<Card> player = new ArrayList<Card>(Arrays.asList(
                new Card('D', 12),
                new Card('S',12),
                new Card('H', 14)
        ));
        assert ThreeCardLogic.compareHands(dealer, player) == 2;
    }

    @Test
    void testDrawPair() {
        // Test draw for pair
        final ArrayList<Card> dealer = new ArrayList<Card>(Arrays.asList(
                new Card('D', 12),
                new Card('H',13),
                new Card('S', 12)
        ));
        final ArrayList<Card> player = new ArrayList<Card>(Arrays.asList(
                new Card('D', 12),
                new Card('S',12),
                new Card('H', 13)
        ));
        assert ThreeCardLogic.compareHands(dealer, player) == 0;
    }

    @Test
    void testPPWinningsStraightFlush() {
        // Test PP winnings for straight flush
        final ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
                new Card('C', 10),
                new Card('C',9),
                new Card('C', 8)
        ));
        assert ThreeCardLogic.evalPPWinnings(hand, 5) == 5 * 40;
    }

    @Test
    void testPPWinningsThreeOfAKind() {
        // Test PP winnings for three of a kind
        final ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
                new Card('C', 14),
                new Card('H', 14),
                new Card('S', 14)
        ));
        assert ThreeCardLogic.evalPPWinnings(hand, 10) == 10 * 30;
    }

    @Test
    void testPPWinningsStraight() {
        // Test PP winnings for straight
        final ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
                new Card('D', 8),
                new Card('C',7),
                new Card('H', 6)
        ));
        assert ThreeCardLogic.evalPPWinnings(hand, 15) == 15 * 6;
    }

    @Test
    void testPPWinningsFlush() {
        // Test PP winnings for flush
        final ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
                new Card('D', 13),
                new Card('D',9),
                new Card('D', 7)
        ));
        assert ThreeCardLogic.evalPPWinnings(hand, 17) == 17 * 3;
    }

    @Test
    void testPPWinningsPairMoreThanTwos() {
        // Test PP winnings for pair
        final ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
                new Card('D', 4),
                new Card('S',4),
                new Card('D', 7)
        ));
        assert ThreeCardLogic.evalPPWinnings(hand, 18) == 18 * 1;
    }

    @Test
    void testPPWinningsPairTwos() {
        // Test PP winnings for Pair of twos
        final ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
                new Card('D', 2),
                new Card('S',2),
                new Card('D', 7)
        ));
        assert ThreeCardLogic.evalPPWinnings(hand, 18) == 18 * 1;
    }

    @Test
    void testPPWinningsForHighCard() {
        // Test PP winnings for higher card
        final ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
                new Card('D', 2),
                new Card('S',14),
                new Card('D', 7)
        ));
        assert ThreeCardLogic.evalPPWinnings(hand, 20) == 0;
    }
}
