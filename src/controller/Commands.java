package controller;

import gameExceptions.GameException;
import model.ItemDB;
import model.RoomDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class: Commands
 *
 * @version 1.2
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * <p>
 * This class handles commands from the user. The command is parsed, the type of
 * command is determined, and then routed to the correct methods to handle the command.
 */
class Commands {

    private static final List<Character> validDirections = Arrays.asList('W', 'N', 'S', 'E', 'U', 'D');
    private static final List<Character> itemCommands = Arrays.asList('I', 'R', 'G');
    public static final int exitCommand = 5;
    private final Player player;
    private Room currentRoom;
    private final RoomDB roomDB;


    /**
     * Method Commands
     * Constructor for the Commands class.
     * Instantiates a new player object for tracking inventory in the game.
     */
    Commands() throws GameException {
        player = new Player();

        try {
            ItemDB itemDB = ItemDB.getInstance();
            itemDB.readItems();

            roomDB = RoomDB.getInstance();
            roomDB.readRooms();

            currentRoom = roomDB.getRoom(player.getCurRoom());
        } catch (GameException e) {
            throw new GameException(e.getMessage());
        }
    }

    private void updateCurrentRoom() throws GameException {

        try {
            currentRoom = roomDB.getRoom(player.getCurRoom());
        } catch (GameException e) {
            throw new GameException(e.getMessage());
        }
    }


    /**
     * Method validateCommand
     * Returns an int that indicates the type of command:
     * 1 for movement (N, S, E, W, U, D)
     * 2 for item commands (G, R, I)
     * 3 for Look (L)
     * 4 for Backpack (B)
     * EXIT_COMMAND for exit (X)
     * Throws an exception for an invalid command.
     *
     * @param cmdLine - String containing the command entered by the user
     * @return int - the integer representing the command received. If not recognized, returns 0 for an invalid command
     * @throws GameException
     */
    private int validateCommand(String cmdLine) throws GameException {
        char cmd;
        try {
            cmd = Character.toUpperCase(cmdLine.charAt(0)); // Get the first character of the command
        } catch (StringIndexOutOfBoundsException e) {
            throw new GameException(e.getMessage());
        }

        // Check if it is a movement command
        if (validDirections.contains(cmd)) {
            return 1;
        }
        // Check if it is an item command
        if (itemCommands.contains(cmd)) {
            return 2;
        }
        // Check if it is a Look command
        if (cmd == 'L') {
            return 3;
        }
        // Check if it is a Backpack command
        if (cmd == 'B') {
            return 4;
        }
        // Check if it is an Exit command
        if (cmd == 'X') {
            return exitCommand;
        }

        throw new GameException(cmdLine + " is not a valid command.\nPlease try again."); // Throw an exception for an invalid command
    }

    /**
     * Method executeCommand
     * Returns the String to be displayed to the user based on the user's command.
     * Calls the correct command method or returns the String for the command entered.
     * Throws an exception for an invalid command.
     *
     * @param cmd - String that contains the command entered by the user
     * @return String - the response to the command
     * @throws GameException
     */
    protected String executeCommand(String cmd) throws GameException {
        int commandType = validateCommand(cmd); // Validate the command
        if (cmd.charAt(0) == 'U') cmd = "N";
        if (cmd.charAt(0) == 'D') cmd = "S";


        return switch (commandType) {
            case 1 -> move(cmd);
            case 2 -> itemCommand(cmd);
            case 3 -> look();
            case 4 -> backpack();
            case exitCommand -> "Game over. Goodbye!";
            default -> throw new GameException("Invalid command.");
        };
    }

    /**
     * Method move
     * returns the String for the new Room the user is entering
     * Calls Room validDirection to ensure that the direction is valid for this room.
     * If the direction is valid,
     * Updates the room to be visited by updating the room
     * Updates the current Room in Player
     * If the direction is not valid,
     * throws an exception for an invalid direction
     *
     * @param cmdRoom - String that contains the command entered by the user
     * @return String - the new room the user is moving to
     * @throws GameException
     */
    private String move(String cmdRoom) throws GameException {

        int validRoomIndex = currentRoom.validDirection(cmdRoom.charAt(0));

        if (validRoomIndex != -1) {
            int designatedRoomID = currentRoom.getExits().get(validRoomIndex).getDestination();
            player.setCurRoom(designatedRoomID);
            roomDB.getRoom(designatedRoomID);
            updateCurrentRoom();

            return "You have entered " + currentRoom.getName();
        }


        throw new GameException();
    }

    /**
     * Method itemCommand
     * Returns the String for the Item based on the user's command.
     * Calls different methods to handle the Item interactions.
     * Throws an exception for an invalid command or action, for example, if the item is not in the room and the user tries to pick it up.
     *
     * @param cmd - String that contains the command entered by the user
     * @return String - the response to the user's command
     * @throws GameException
     */
    private String itemCommand(String cmd) throws GameException {
        char itemCmd = Character.toUpperCase(cmd.charAt(0)); // Get the item command character
        String[] cmdLines = cmd.split(" ", 2);
        String itemName = null;
        try {
            itemName = cmdLines[1]; //grabs Item name
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GameException("Please specify item name");
        }

        return switch (itemCmd) {
            case 'G' -> get(itemName);
            case 'R' -> remove(itemName);
            case 'I' -> inspect(itemName);
            default -> throw new GameException("Invalid command.");
        };
    }


    /**
     * Method get
     * Returns the String for the item to be added to the Player's inventory.
     * Updates the room to remove the item from the room.
     * Updates Player to add the item to the backpack.
     * Throws an exception if the item is not in the room.
     *
     * @return String - the item has been added to the inventory
     * @throws GameException
     */
    private String get(String itemName) throws GameException {
        ArrayList<Item> roomItems = currentRoom.getRoomItems();
        for (Item item : roomItems) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                player.addItem(item);
                currentRoom.removeItem(item);
                return item.getItemName() + " was added to your inventory";
            }
        }

        throw new GameException("Failed to get " + itemName);
    }

    /**
     * Method remove
     * Returns the String for dropping the item.
     * Updates the room to add the item to the room.
     * Updates Player by removing the item from the backpack.
     * Throws an exception if the item is not in the inventory.
     *
     * @return String - the item has been dropped
     * @throws GameException
     */
    private String remove(String itemName) throws GameException {

        if (player.getInventory() == null) {
            throw new GameException("You don't have any item to drop.");
        }

        for (Item item : player.getInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                // Remove the item from the player's inventory
                player.removeItem(item);
                // Add the item to the current room
                currentRoom.dropItem(item);
                return "You removed " + item.getItemName() + " from your inventory";
            }
        }
        throw new GameException("Failed to drop" + itemName);
    }

    /**
     * Method look
     * Returns the String for looking at the current room.
     *
     * @return String - the description of the current room
     */
    private String look() throws GameException {
//        return currentRoom.getDescription();
        ArrayList<Item> roomItems = currentRoom.getRoomItems();
        ArrayList<Exit> roomExits = currentRoom.getExits();
        String roomVisited;

        if (currentRoom.isVisited()) roomVisited = "You have been here before.\n";
        else {
            roomVisited = "This is your first time here.\n";
        }
        currentRoom.visitedRoom();

        StringBuilder exitString = new StringBuilder("Available exits:");
        for (Exit exit : roomExits) {
            Room rm = new Room();
            rm.retrieveByID(exit.getDestination());
            exitString.append("\n").append("-").append(exit.getDirection());
        }
        StringBuilder itemString;
        if (roomItems.isEmpty()) itemString = new StringBuilder("No items currently in this room\n");
        else {
            itemString = new StringBuilder("Items in the room:\n");
            for (Item item : roomItems) {
                itemString.append("-").append(item.getItemName()).append("\n");
            }
        }

        return "You are in the " + currentRoom.getName() + "\n" +
                currentRoom.getDescription() + roomVisited + "----------\n" +
                itemString + "----------\n" +
                exitString;
    }

    /**
     * Method backpack
     * Returns the String for displaying the items in the player's backpack.
     *
     * @return String - the items in the backpack
     */
    private String backpack() {
        return player.printInventory();
    }

    /**
     * Method inspect
     * Returns the String for inspecting the current item in the room.
     *
     * @return String - the description of the current item or a message if there is no item
     */
    private String inspect(String itemName) throws GameException {
        ArrayList<Item> roomItems = currentRoom.getRoomItems();

        if (roomItems == null) {
            return "There is no item to inspect in this room.";
        } else {
            for (Item item : roomItems) {
                if (itemName.equalsIgnoreCase(item.getItemName())) return item.getItemDescription();
            }
        }
        return "Sorry " + itemName + " is not in this room";
    }
}

