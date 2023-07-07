package controller;

import gameExceptions.GameException;
import model.RoomDB;

import java.util.ArrayList;

/**
 * Class: GameController
 *
 * @version 1.2
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023,
 * This class is the controller interface for mini-game 2
 */
public class GameController {

    private final Commands commands;


    /**
     * Method GameController
     * Constructor for the GameController class
     * Instantiates the Commands object for the game
     */
    public GameController() {
        try {
            commands = new Commands();
        } catch (GameException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method displayFirstRoom
     * Retrieves the String for the first room
     *
     * @return String - the first room display String
     * @throws GameException - if the first room is not found.
     */
    public String displayFirstRoom() throws GameException {
        //Looks by default
        return executeCommand("L");
    }

    /**
     * Method executeCommand
     * Handles the user input from Adventure
     * Sends the user's command to Commands for processing
     * throws an exception if the command is not valid
     *
     * @param cmd - String
     * @return String - the result from the command
     * @throws GameException if the command is invalid
     */
    public String executeCommand(String cmd) throws GameException {
        if (cmd.equalsIgnoreCase("x")) {
            return "  ";
        }
        return commands.executeCommand(cmd.toUpperCase());
    }

    /**
     * Method printMap
     * Handles the print map command from Adventure
     * Builds a String representation of the current map
     *
     * @throws GameException if one of the files is not found or has an error
     */
    public void printMap() throws GameException {
        RoomDB rdb = RoomDB.getInstance();
        ArrayList<Room> rooms = rdb.getRoomArray();
        StringBuilder map = new StringBuilder("Room Info:\n");
        for (Room room : rooms) {
            map.append(room.getName()).append(" - ").append(room.getDescription()).append("\n");
        }
        map.append("----------\n");
    }

}
