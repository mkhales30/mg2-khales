package view;

import controller.GameController;
import gameExceptions.GameException;

import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Class: Adventure
 *
 * @version 2.0
 * @Original Author: Dr. Rick Price, Khales Rahman
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * Revised: June 16, 2023
 * <p>
 * This class is the UI class for Mini Game 2. This class will control all
 * user aspects
 * of these games.
 * Revised version and will run now
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
        System.out.println("To exit(X) the game, enter x");
        System.out.println("----------");

        String display;
        try {
            display = gc.displayFirstRoom();
            System.out.println(display);
            System.out.println("----------");
        } catch (GameException e) {
            System.out.println(e.getMessage());
        }

        String response = "";
        System.out.print("Enter a command: ");
        String command;
        boolean firstGo = true;
        do {
            if (!firstGo) {
                System.out.print("----------\n");
                System.out.print("Enter another command: ");
            }
            firstGo = false;
            command = getCommand();
            try {
                response = gc.executeCommand(command);
                System.out.println(response);
            } catch (GameException ge) {
                System.out.println(ge.getMessage());
            }
        } while (!response.equalsIgnoreCase("  "));

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
            ad.gc.printMap();
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
