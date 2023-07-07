package gameExceptions;

/**
 * Class: GameException
 *
 * @version 1.2
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * <p>
 * This class is the custom exception for the game.
 */
public class GameException extends Exception {

    public GameException() {
        super();
    }

    /**
     *
     */
    public GameException(String message) {
        super(message);
    }

}
