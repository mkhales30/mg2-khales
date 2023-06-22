package view;

import controller.GameController;
import gameExceptions.GameException;

import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Class: Adventure
 *
 * @version 1.2
 * @Original Author: Dr. Rick Price, Khales Rahman
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * Revised: June 16, 2023
 * <p>
 * This class is the UI class for Mini Game 2. This class will control all
 * user aspects
 * of these games.
 * This version does not run yet and still needs multiple revisions
 */
public class Adventure {

    private final Scanner input;
    private final GameController gc;

    /**
     * Method Adventure
     * Constructor for the Adventure class
     * Creates an instance of the GameController class which is the interface into the controller package
     */
    public Adventure() {

        gc = new GameController();
        input = new Scanner(System.in);
    }

    /**
     * Method playGame
     * Allows the player to play the game.
     * Prints an introduction message
     * Loops until the user chooses to exit.
     * Prints the current rooms display text if the direction is valid.
     * If an invalid direction is entered, catches the exception and prints the message in that exception.
     * Calls getCommand to get users input.
     * Passes the user's command to Commands, executeCommand for processing. This will handle move, item, look,
     * and backpack commands.
     */
    private void playGame() throws FileNotFoundException, GameException {
        System.out.println("Welcome to my adventure game. You will proceed through rooms based upon your entries.");
        System.out.println("You can navigate by using the entire direction or just the first letter.");
        System.out.println("You can view a room using the 'Look' command.");
        System.out.println("To exit(X) the game, enter x\n");
        String display = "";
        try {
            display = "You are in " + gc.displayFirstRoom();
            System.out.println(display);
        } catch (GameException e) {
            System.out.println(e.getMessage());
        }
//        Game theGame = new Game();
//        theGame.play();

		String response = "";
		do {
			try {
				String command = getCommand();
				System.out.println(response = gc.executeCommand(command));
		} catch(GameException ge) {
			System.out.println(ge.getMessage() + "\n");
			}
		} while(!response.equalsIgnoreCase("Exit"));

		System.out.println("Thank you for playing my game.");
    }

    /**
     * Method getCommand
     * Prompts the user for their input and returns this to playGame
     *
     * @return String - the command entered by the user.
     */
    private String getCommand() {
        return input.nextLine();
    }

    /**
     * Method main
     * Starts the game, initializes the Scanner, calls playGame and then closes the Scanner
     * If the data file is not found, prints a message and exits.
     * If the data file is found and successfully loaded, prints the map by calling the
     * GameController printMap method and printing the String that methods returns.
     *
     * @param args - String[]
     */
    public static void main(String[] args) throws FileNotFoundException, GameException {

        Adventure ad = new Adventure();
        boolean fileFound = false;
        try {
            System.out.println(ad.gc.printMap());
            fileFound = true;
        } catch (GameException ge) {
            System.out.println(ge.getMessage());
        }

        if (fileFound) {
            ad.playGame();
        } else {
            System.out.println("Could not load game :(");
        }
        ad.input.close();


    }

}
