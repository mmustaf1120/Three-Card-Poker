import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * ThreeCardPokerGame is a root of the application.
 * It sets up the board, players, dealer, scenes, and menu options.
 */
public class ThreeCardPokerGame extends Application {
    Player playerOne;
    Player playerTwo;
    Dealer theDealer;
    Deck theDeck;
    Boolean gameLook = true;
    PokerBoard board;

    public static void main(String[] args) {

        // TODO Auto-generated method stub
        launch(args);
    }

    //feel free to remove the starter code from this method
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        primaryStage.setTitle("Let's Play Three Card Poker!!!");
        MenuBar menuBar = createMenuBar(primaryStage);
        theDealer = new Dealer();
        playerOne = new Player();
        playerTwo = new Player();
        VBox vBox = new VBox(menuBar);
        theDeck = new Deck();

        BorderPane root = new BorderPane();
        board = new PokerBoard(800, 600, theDealer, theDeck, playerOne, playerTwo);
        board.addRoot(root);
        root.setTop(vBox);

        Scene scene = new Scene(root, 800, 625);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void freshStart() {
        playerTwo.reset();
        playerOne.reset();
        playerOne.resetTotalWinning();
        playerTwo.resetTotalWinning();
        theDealer.resetDeck();
        theDealer.resetHand();
        board.clearPlayer1Cards();
        board.clearPlayer2Cards();
        board.clearDealerCards();
        board.resetText();
        board.resetUIState();
        board.setPlayerAnte(5, 1);
        board.setPlayerAnte(5, 2);
        board.setPlayerPlay(0, 1);
        board.setPlayerPlay(0, 2);
        board.setPlayerPlus(0,1);
        board.setPlayerPlus(0,2);
        GameState.resetState();

    }

    public void exit() {
        System.exit(0);
    }

    public void changeLook() {
        gameLook  = !gameLook;
       String imageUrl = gameLook ? "board.jpg" :  "board2.jpg" ;
       board.setBoardImage(imageUrl);
    }


    private  MenuBar createMenuBar(Stage primaryStage){
        Menu m = new Menu("Options");
        MenuItem m1 = new MenuItem("Fresh Start");
        MenuItem m2 = new MenuItem("Change look");
        MenuItem m3 = new MenuItem("Exit");
        MenuBar menuBar = new MenuBar();
        m.getItems().add(m1);
        m.getItems().add(m2);
        m.getItems().add(m3);
        menuBar.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));

        EventHandler<ActionEvent> freshStartEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                freshStart();
            }
        };
        EventHandler<ActionEvent> changeLookEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                changeLook();
            }
        };
        EventHandler<ActionEvent> exitEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                exit();
            }
        };

        // add event
        m1.setOnAction(freshStartEvent);
        m2.setOnAction(changeLookEvent);
        m3.setOnAction(exitEvent);
        menuBar.getMenus().add(m);
        return  menuBar;
    }
}
