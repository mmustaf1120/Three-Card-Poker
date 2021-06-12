import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Objects;

/**
 * PokerBoard manages UI of the game
 */
public class PokerBoard {
    private float width;
    private float height;
    private Dealer theDealer;
    private Deck theDeck;
    private Player playerOne;
    private Player playerTwo;

    private Bounds dealerCardBound;
    private Bounds player1CardBound;
    private Bounds player2CardBound;

    private Image backImage = loadCardImage("cards/back.png");

    private Rectangle dealerCard1;
    private Rectangle dealerCard2;
    private Rectangle dealerCard3;

    private Rectangle player1Card1;
    private Rectangle player1Card2;
    private Rectangle player1Card3;

    private Rectangle player2Card1;
    private Rectangle player2Card2;
    private Rectangle player2Card3;

    private Text player1Ante;
    private Text player1Plus;
    private Text player1Play;

    private Text player2Ante;
    private Text player2Plus;
    private Text player2Play;

    private Text player1TotalWining;
    private Text player2TotalWining;

    private String winingText = "Winings : $";

    private Pane boardRenderObject;

    private Text continueText;
    Button cntButton;

    private String plusText = "Plus    : $";
    private String anteText = "Ante   : $";
    private String playText = "Play   : $";

    private Button player1AnteIncrease;
    private Button player1AnteDecrease;
    private Button player1PlusIncrease;
    private Button player1PlusDecrease;

    private Button player2AnteIncrease;
    private Button player2AnteDecrease;
    private Button player2PlusIncrease;
    private Button player2PlusDecrease;

    boolean playerOneFolded = false;
    boolean playerTwoFolded = false;

    PokerBoard(float width, float height, Dealer theDealer, Deck theDeck, Player playerOne, Player playerTwo) {
        this.width = width;
        this.height = height;
        this.theDealer = theDealer;
        this.theDeck = theDeck;
        this.theDeck.size();
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;


        this.boardRenderObject = new Pane();
        this.boardRenderObject.setPrefSize(width, height);
        this.setBoardImage("board.jpg");

        // Draw text
        drawFancyText(this.boardRenderObject, (width / 2.0f) - 170.0f, 75.0f, 50.0f, "Dealers Hand", 2);
        // dealers hands
        dealerCardBound = new Bounds((width / 2.0f) - 80.0f, 100, 50, 75, 10f, 0.0f);
        dealerCard1 = drawCardSlot(this.boardRenderObject, dealerCardBound.x, dealerCardBound.y, dealerCardBound.width, dealerCardBound.height);
        dealerCard2 = drawCardSlot(this.boardRenderObject, dealerCardBound.x + dealerCardBound.xOffset + dealerCardBound.width, dealerCardBound.y, dealerCardBound.width, dealerCardBound.height);
        dealerCard3 = drawCardSlot(this.boardRenderObject, dealerCardBound.x + dealerCardBound.xOffset * 2.0f + dealerCardBound.width * 2.0f, dealerCardBound.y, dealerCardBound.width, dealerCardBound.height);

        // player 1
        player1Plus = setupNormaText(this.boardRenderObject, 170.0f, height / 2.0f + 30.0f, 20, plusText+"0.0");
        player1Ante = setupNormaText(this.boardRenderObject, 170.0f, height / 2.0f + 60.0f, 20, anteText+"5.0");
        player1Play = setupNormaText(this.boardRenderObject, 170.0f, height - 60.0f, 20, playText+"0.0");
        player1TotalWining = setupNormaText(this.boardRenderObject, 170.0f, height - 40.0f, 20, winingText+"0");
        player1AnteIncrease = new Button("+");
        player1AnteDecrease = new Button("--");
        player1PlusIncrease = new Button("+");
        player1PlusDecrease = new Button("--");
        player1AnteIncrease.setLayoutX(130.0f);
        player1AnteIncrease.setLayoutY(height / 2.0f + 40.0f);
        player1AnteDecrease.setLayoutX(100.0f);
        player1AnteDecrease.setLayoutY(height / 2.0f + 40.0f);
        player1PlusIncrease.setLayoutX(130.0f);
        player1PlusIncrease.setLayoutY(height / 2.0f + 10.0f);
        player1PlusDecrease.setLayoutX(100.0f);
        player1PlusDecrease.setLayoutY(height / 2.0f + 10.0f);
        this.boardRenderObject.getChildren().addAll(player1AnteIncrease, player1AnteDecrease, player1PlusDecrease, player1PlusIncrease);
        drawFancyText(this.boardRenderObject, 100.0f, height - 200, 30.0f, "Player1's Hand", 2);
        player1CardBound = new Bounds(135.0f, height - 175, 50, 75, 10f, 0.0f);
        player1Card1 = drawCardSlot(this.boardRenderObject, player1CardBound.x, player1CardBound.y, player1CardBound.width, player1CardBound.height);
        player1Card2 = drawCardSlot(this.boardRenderObject, player1CardBound.x + dealerCardBound.xOffset + dealerCardBound.width, player1CardBound.y, player1CardBound.width, player1CardBound.height);
        player1Card3 = drawCardSlot(this.boardRenderObject, player1CardBound.x + dealerCardBound.xOffset * 2.0f + dealerCardBound.width * 2.0f, player1CardBound.y, player1CardBound.width, player1CardBound.height);

        // player 2
        player2Plus = setupNormaText(this.boardRenderObject, width / 2.0f + 140.0f, height / 2.0f + 30.0f, 20, plusText+"0.0");
        player2Ante = setupNormaText(this.boardRenderObject, width / 2.0f + 140.0f, height / 2.0f + 60.0f, 20, anteText+"5.0");
        player2Play = setupNormaText(this.boardRenderObject, width / 2.0f + 140.0f, height - 60.0f, 20, playText+"0.0");
        player2TotalWining = setupNormaText(this.boardRenderObject, width / 2.0f + 140.0f, height - 40.0f, 20, winingText+"0");
        player2AnteIncrease = new Button("+");
        player2AnteDecrease = new Button("--");
        player2PlusIncrease = new Button("+");
        player2PlusDecrease = new Button("--");
        player2AnteIncrease.setLayoutX(width / 2.0f + 100.0f);
        player2AnteIncrease.setLayoutY(height / 2.0f + 40.0f);
        player2AnteDecrease.setLayoutX(width / 2.0f + 70.0f);
        player2AnteDecrease.setLayoutY(height / 2.0f + 40.0f);
        player2PlusIncrease.setLayoutX(width / 2.0f + 100.0f);
        player2PlusIncrease.setLayoutY(height / 2.0f + 10.0f);
        player2PlusDecrease.setLayoutX(width / 2.0f + 70.0f);
        player2PlusDecrease.setLayoutY(height / 2.0f + 10.0f);
        this.boardRenderObject.getChildren().addAll(player2AnteIncrease, player2AnteDecrease, player2PlusDecrease, player2PlusIncrease);
        drawFancyText(this.boardRenderObject, width / 2.0f + 65.0f, height - 200, 30.0f, "Player2's Hand", 2);
        player2CardBound = new Bounds(width / 2.0f + 105.0f, height - 175, 50, 75, 10f, 0.0f);
        player2Card1 = drawCardSlot(this.boardRenderObject, player2CardBound.x, player2CardBound.y, player2CardBound.width, player2CardBound.height);
        player2Card2 = drawCardSlot(this.boardRenderObject, player2CardBound.x + dealerCardBound.xOffset + dealerCardBound.width, player2CardBound.y, player2CardBound.width, player2CardBound.height);
        player2Card3 = drawCardSlot(this.boardRenderObject, player2CardBound.x + dealerCardBound.xOffset * 2.0f + dealerCardBound.width * 2.0f, player2CardBound.y, player2CardBound.width, player2CardBound.height);

        // Draw buttons
        drawDecreaseAnteButton(playerOne, 1);
        drawIncreaseAnteButton(playerOne, 1);
        drawIncreasePairPlusButton(playerOne, 1);
        drawDecreasePairPlusButton(playerOne, 1);

        drawDecreaseAnteButton(playerTwo, 2);
        drawIncreaseAnteButton(playerTwo, 2);
        drawIncreasePairPlusButton(playerTwo, 2);
        drawDecreasePairPlusButton(playerTwo, 2);

        disablePlayerOneButtons(true);
        disablePlayerTwoButtons(true);

        continueText = drawFancyText(this.boardRenderObject, width / 2 - 100, height / 2, 18.0f, "THREE CARD POKER", 1);
        continueText = drawFancyText(this.boardRenderObject, width / 2 - 150, height / 2 - 100, 18.0f, "THREE CARD POKER", 1);
        cntButton = new Button("Start");
        cntButton.setLayoutX(width / 2);
        cntButton.setLayoutY(height - 50);
        drawContinueButton();
    }

    void resetPlayerBets(Player player, int playerNo) {
        player.losePairPlusBet();
        player.loseAnteBet();
        player.losePlayBet();

        if (playerNo == 1) {
            setPlayerPlay(0, 1);
            setPlayerAnte(5, 1);
            setPlayerPlus(0, 1);
        } else {
            setPlayerPlay(0, 2);
            setPlayerAnte(5,2 );
            setPlayerPlus(0,2 );
        }
    }

    // This function is the core of the game flow.
    // The state of the game is controlled by this method
    void drawContinueButton() {
        Button fold = new Button("fold");
        fold.setLayoutX(width / 2.0f - 50.0f);
        fold.setLayoutY(height - 50.0f);
        fold.setDisable(true);
        cntButton.setLayoutX(width / 2.0f);
        cntButton.setLayoutY(height - 50.0f);

        fold.setOnAction(e -> {
            if (GameState.currentState == GameState.PLAYER_ONE_PLAY) {
                playerOneFolded = true;
                resetPlayerBets(playerOne, 1);
                cntButton.fire();
            } else if (GameState.currentState == GameState.PLAYER_TWO_PLAY) {
                playerTwoFolded = true;
                resetPlayerBets(playerTwo, 2);
                cntButton.fire();
            }
        });
        cntButton.setOnAction(e -> {
            disablePlayerTwoButtons(true);
            disablePlayerOneButtons(true);
            fold.setDisable(true);
            if (GameState.currentState == 0) {
                cntButton.setText("Continue");
                disablePlayerOneButtons(false);
                GameState.nextState();
                continueText.setText("Player One's turn to bet");
                // Enable ante and pairplus
            } else if (GameState.currentState == GameState.PLAYER_ONE_START) {
                disablePlayerTwoButtons(false);
                GameState.nextState();
                continueText.setText("Player Two's turn to bet");
            } else if (GameState.currentState == GameState.PLAYER_TWO_START) {
                GameState.nextState();
                continueText.setText(("Cards are served. Please Continue."));
                playerOne.hand = theDealer.dealHand();
                playerTwo.hand = theDealer.dealHand();
                theDealer.dealersHand = theDealer.dealHand();

                // set img
                System.out.println(playerOne.hand.get(0).getName());
                player1Card1.setFill(new ImagePattern(loadCardImage("cards/" + playerOne.hand.get(0).getName() + ".png")));
                player1Card2.setFill(new ImagePattern(loadCardImage("cards/" + playerOne.hand.get(1).getName() + ".png")));
                player1Card3.setFill(new ImagePattern(loadCardImage("cards/" + playerOne.hand.get(2).getName() + ".png")));

                // set img
                player2Card1.setFill(new ImagePattern(loadCardImage("cards/" + playerTwo.hand.get(0).getName() + ".png")));
                player2Card2.setFill(new ImagePattern(loadCardImage("cards/" + playerTwo.hand.get(1).getName() + ".png")));
                player2Card3.setFill(new ImagePattern(loadCardImage("cards/" + playerTwo.hand.get(2).getName() + ".png")));

                dealerCard1.setFill(new ImagePattern(backImage));
                dealerCard2.setFill(new ImagePattern(backImage));
                dealerCard3.setFill(new ImagePattern(backImage));
            } else if (GameState.currentState == GameState.CONTINUE_STATE) {
                fold.setDisable(false);
                GameState.nextState();
                continueText.setText("Player One Turn: Wager or Fold");
                continueText.setText("Player One Turn: Wager or Fold");
                cntButton.setText("Bet Play Wager");
            } else if (GameState.currentState == GameState.PLAYER_ONE_PLAY) {
                fold.setDisable(false);
                continueText.setText("Player Two Turn: Wager or Fold");
                cntButton.setText("Bet Play Wager");
                if (!playerOneFolded) {
                    playerOne.setPlayBet();
                    setPlayerPlay(playerOne.playBet, 1);
                }
                GameState.nextState();
            } else if (GameState.currentState == GameState.PLAYER_TWO_PLAY) {
                continueText.setText("Wagers have been set. Please continue.");
                if (!playerTwoFolded) {
                    playerTwo.setPlayBet();
                    setPlayerPlay(playerTwo.playBet, 2);
                }
                GameState.nextState();
                cntButton.setText("Continue");
            } else if (GameState.currentState == GameState.DEALER_SHOWS) {
                GameState.nextState();
                fold.setDisable(true);
                cntButton.setText("Continue");
                continueText.setText("Dealer shows hand");
                dealerCard1.setFill(new ImagePattern(loadCardImage("cards/" + theDealer.dealersHand.get(0).getName() + ".png")));
                dealerCard2.setFill(new ImagePattern(loadCardImage("cards/" + theDealer.dealersHand.get(1).getName() + ".png")));
                dealerCard3.setFill(new ImagePattern(loadCardImage("cards/" + theDealer.dealersHand.get(2).getName() + ".png")));
            } else if (GameState.currentState == GameState.PLAYER_ONE_SCORE) {
                String text = "";

                if (playerOneFolded) {
                    text = "Player One folded and so did not win anything";
                } else if (!theDealer.atLeastQueenHigh()) {
                    text = "Dealer got less than Queen High \n so Player One won ante bet: $" + playerOne.anteBet;
                    playerOne.totalWinnings += playerOne.anteBet;
                } else {
                    final int ppWon = ThreeCardLogic.evalPPWinnings(playerOne.hand, playerOne.pairPlusBet);
                    playerOne.totalWinnings += ppWon;
                    text += "Player One won pair plus bet: $" + ppWon;

                    final int whoWon = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
                    text += "\n";
                    if (whoWon == 1) {
                        text += "Player One lost to dealer";
                    } else if (whoWon == 2) {
                        int won = playerOne.anteBet + playerOne.playBet;
                        text += "Player One won dealer: $" + won;
                        playerOne.totalWinnings += won;
                    } else {
                        text += "Player One drawed with Dealer";
                    }
                }

                player1TotalWining.setText(winingText + playerOne.totalWinnings);
                continueText.setText(text);
                GameState.nextState();
            } else if (GameState.currentState == GameState.PLAYER_TWO_SCORE) {
                String text = "";
                if (playerTwoFolded) {
                    text = "Player Two folded and so did not win anything";
                } else if (!theDealer.atLeastQueenHigh()) {
                    text = "Dealer got less than Queen High \n so Player Two won ante bet: $" + playerTwo.anteBet;
                    playerTwo.totalWinnings += playerTwo.anteBet;
                } else {
                    final int ppWon = ThreeCardLogic.evalPPWinnings(playerTwo.hand, playerTwo.pairPlusBet);
                    playerTwo.totalWinnings += ppWon;
                    text += "Player Two won pair plus bet: $" + ppWon;
                    final int whoWon = ThreeCardLogic.compareHands(theDealer.dealersHand, playerTwo.hand);
                    text += "\n";
                    if (whoWon == 1) {
                        text += "Player Two lost to dealer";
                    } else if (whoWon == 2) {
                        int won = playerTwo.anteBet + playerTwo.playBet;
                        text += "Player Two won dealer: $" + won;
                        playerTwo.totalWinnings += won;
                    } else {
                        text += "Player Two drawed with Dealer";
                    }
                }

                player2TotalWining.setText(winingText + playerTwo.totalWinnings);
                continueText.setText(text);
                GameState.nextState();
            } else if (GameState.currentState == GameState.END) {
                playerOneFolded = false;
                playerTwoFolded = false;
                resetPlayerBets(playerOne, 1);
                resetPlayerBets(playerTwo, 2);
                continueText.setText("Round complete. Continue to start another round");
                playerOne.reset();
                playerTwo.reset();
                theDealer.resetHand();
                clearPlayer1Cards();
                clearPlayer2Cards();
                clearDealerCards();
                GameState.nextState();
            }
        });
        this.boardRenderObject.getChildren().addAll(cntButton, fold);
    }

    void drawIncreaseAnteButton(Player player, int playerNo) {
        Button button = (playerNo == 1) ? player1AnteIncrease : player2AnteIncrease;
        button.setOnAction(e -> {
            player.increaseAnteBet();
            setPlayerAnte(player.anteBet, playerNo);
        });
    }

    void drawDecreaseAnteButton(Player player, int playerNo) {
        Button button = (playerNo == 1) ? player1AnteDecrease : player2AnteDecrease;
        button.setOnAction(e -> {
            player.decreaseAnteBet();
            setPlayerAnte(player.anteBet, playerNo);
        });
    }

    void drawIncreasePairPlusButton(Player player, int playerNo) {
        Button button = (playerNo == 1) ? player1PlusIncrease : player2PlusIncrease;
        button.setOnAction(e -> {
            player.increasePairPlusBet();
            setPlayerPlus(player.pairPlusBet, playerNo);
        });
    }

    void drawDecreasePairPlusButton(Player player, int playerNo) {
        Button button = (playerNo == 1) ? player1PlusDecrease : player2PlusDecrease;
        button.setOnAction(e -> {
            player.decreasePairPlusBet();
            setPlayerPlus(player.pairPlusBet, playerNo);
        });
    }

    void addRoot(BorderPane root) {
        root.setCenter(this.boardRenderObject);
    }

    void addChildren(Node children) {
        this.boardRenderObject.getChildren().add(children);
    }

    public void setPlayer1Cards(String card1, String card2, String card3) {
        player1Card1.setFill(new ImagePattern(loadCardImage("cards/" + card1 + ".png")));
        player1Card2.setFill(new ImagePattern(loadCardImage("cards/" + card2 + ".png")));
        player1Card3.setFill(new ImagePattern(loadCardImage("cards/" + card3 + ".png")));
    }

    public void clearPlayer1Cards() {
        player1Card1.setFill(Color.TRANSPARENT);
        player1Card2.setFill(Color.TRANSPARENT);
        player1Card3.setFill(Color.TRANSPARENT);
    }

    public void setPlayer2Cards(String card1, String card2, String card3) {
        player2Card1.setFill(new ImagePattern(loadCardImage("cards/" + card1 + ".png")));
        player2Card2.setFill(new ImagePattern(loadCardImage("cards/" + card2 + ".png")));
        player2Card3.setFill(new ImagePattern(loadCardImage("cards/" + card3 + ".png")));
    }

    public void resetText() {
        continueText.setText("THREE CARD POKER");
    }

    public void resetUIState() {
        disablePlayerOneButtons(true);
        disablePlayerTwoButtons(true);
        cntButton.setText("Start");
        playerTwoFolded = false;
        playerOneFolded = false;
        player2TotalWining.setText(winingText + 0);
        player1TotalWining.setText(winingText + 0);
    }

    public void clearPlayer2Cards() {
        player2Card1.setFill(Color.TRANSPARENT);
        player2Card2.setFill(Color.TRANSPARENT);
        player2Card3.setFill(Color.TRANSPARENT);
    }

    public void clearDealerCards() {
        dealerCard1.setFill(Color.TRANSPARENT);
        dealerCard2.setFill(Color.TRANSPARENT);
        dealerCard3.setFill(Color.TRANSPARENT);
    }

    public void setPlayerAnte(float ante, int playerNo) {
        if (playerNo == 1)
            player1Ante.setText(anteText + ante);
        else
            player2Ante.setText(anteText + ante);
    }

    public void setPlayerPlus(float plus, int playerNo) {
        if (playerNo == 1)
            player1Plus.setText(plusText + plus);
        else
            player2Plus.setText(plusText + plus);
    }

    public void setPlayerPlay(float play, int playerNo) {
        if (playerNo == 1)
            player1Play.setText(playText + play);
        else
            player2Play.setText(playText + play);

    }

    private Image loadCardImage(String path) {
        return new Image(getClass().getResourceAsStream(path), 50.0, 75.0, false, true);
    }

    private Rectangle drawCardSlot(Pane pane, float x, float y, float width, float height) {
        Rectangle cardBox = new Rectangle();
        cardBox.setFill(Color.TRANSPARENT);
        cardBox.setStroke(Color.WHEAT);
        cardBox.setStrokeWidth(5);
        cardBox.setX(x);
        cardBox.setY(y);
        cardBox.setWidth(width);
        cardBox.setHeight(height);
        cardBox.setArcHeight(height * 0.1);
        cardBox.setArcWidth(width * 0.1);
        pane.getChildren().add(cardBox);
        return cardBox;
    }

    private Text drawFancyText(Pane pane, float x, float y, float size, String textStr, float strokeWidth) {
        Text text = new Text();
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, size));
        text.setX(x);
        text.setY(y);
        text.setFill(Color.BROWN);
        text.setStrokeWidth(strokeWidth);
        text.setStroke(Color.GOLD);
        text.setText(textStr);
        pane.getChildren().add(text);
        return text;
    }


    private Text setupNormaText(Pane pane, float x, float y, float size, String textStr) {
        Text text = new Text();
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, size));
        text.setX(x);
        text.setY(y);
        text.setFill(Color.WHEAT);
        text.setStrokeWidth(1);
        text.setStroke(Color.BROWN);
        text.setText(textStr);
        pane.getChildren().addAll(text);
        return text;
    }


    public void disablePlayerOneButtons(boolean disable) {
        player1AnteIncrease.setDisable(disable);
        player1AnteDecrease.setDisable(disable);
        player1PlusDecrease.setDisable(disable);
        player1PlusIncrease.setDisable(disable);
    }

    public void disablePlayerTwoButtons(boolean disable) {
        player2AnteIncrease.setDisable(disable);
        player2AnteDecrease.setDisable(disable);
        player2PlusDecrease.setDisable(disable);
        player2PlusIncrease.setDisable(disable);
    }

    public void setBoardImage(String imageUrl) {
        this.boardRenderObject.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(PokerBoard.class.getClassLoader().getResourceAsStream(imageUrl)), width, height, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

    }

    public void addChangeLook() {

    }
}
