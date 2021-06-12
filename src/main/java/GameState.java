/**
 * GameState represents a state in the game that is controlled by continue button on board.
 * It is how the flow of game is maintained.
 */
public class GameState {
    public static int currentState = 0;

    final public static int PLAYER_ONE_START = 1;
    final public static int PLAYER_TWO_START = 2;
    final public static int CONTINUE_STATE = 3;
    final public static int PLAYER_ONE_PLAY = 4;
    final public static int PLAYER_TWO_PLAY = 5;

    final public static int DEALER_SHOWS = 6;

    final public static int PLAYER_ONE_SCORE = 7;
    final public static int PLAYER_TWO_SCORE = 8;
    final public static int END = 9;

    public static void nextState() {
        if (currentState == 9) {
            currentState = 0;
        } else {
            currentState++;
        }
    }

    public static void resetState() {
        currentState = 0;
    }
}
