package controller;

import gameExceptions.GameException;

/**
 * Class: GameController
 * @version 1.2
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * This class � Is the controller interface for mini game 2
 */
public class GameController {

	public static final int FIRST_ROOM = 1;
	private Commands commands;

	/**
	 * Method GameController
	 * Constructor for the GameController class
	 * Instatiates the Commands object for the game
	 */
	public GameController() {
		// TODO - implement GameController.GameController
		throw new UnsupportedOperationException();
	}

	/**
	 * Method displayFirstRoom
	 * Retrieves the String for the first room
	 * @return - the first room display String
	 * @throws GameException - if the first room is not found.
	 */
	public String displayFirstRoom() throws GameException {
		// TODO - implement GameController.displayFirstRoom
		throw new UnsupportedOperationException();
	}

	/**
	 * Method executeCommand
	 * Handles the user input from Adventure
	 * Sends the user's command to Commands for processing
	 * throws an exception if the command is not valid
	 * @param cmd - String
	 * @return String - the result from the command
	 * @throws GameException if the command is invalid
	 */
	public String executeCommand(String cmd) throws GameException {
		// TODO - implement GameController.executeCommand
		throw new UnsupportedOperationException();
	}

	/**
	 * Method printMap
	 * Handles the print map command from Adventure
	 * Builds a  String representation of the current map
	 * @return String - the String of the current map
	 * @throws GameException if one of the files is not found or has an error
	 */
	public String printMap() throws GameException {
		// TODO - implement GameController.printMap
		throw new UnsupportedOperationException();
	}

}