package controller;

import gameExceptions.GameException;

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
    private Player player;
    private Room currentRoom;

    /**
     * Method Commands
     * Constructor for the Commands class.
     * Instantiates a new player object for tracking inventory in the game.
     */
    Commands() {
        player = new Player(); // Instantiate a new Player object
//        currentRoom = rooms.get(0);
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
        char cmd = Character.toUpperCase(cmdLine.charAt(0)); // Get the first character of the command

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

        throw new GameException("Invalid command."); // Throw an exception for an invalid command
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

        switch (commandType) {
            case 1:
                return "move(cmd)";
            case 2:
                return itemCommand(cmd);
            case 3:
                return look();
            case 4:
                return backpack();
            case exitCommand:
                return "Game over. Goodbye!";
            default:
                throw new GameException("Invalid command.");
        }
    }

//	/**
//	 * Method move
//	 * Returns the String for the new Room the user is entering.
//	 * Calls the Room validDirection method to ensure that the direction is valid for this room.
//	 * If the direction is valid, updates the room to be visited by updating the room.
//	 * Updates the current Room in Player.
//	 * If the direction is not valid, throws an exception for an invalid direction.
//	 *
//	 * @param cmdRoom - String that contains the command entered by the user
//	 * @return String - the new room the user is moving to
//	 * @throws GameException
//	 */
//	private int move(String cmdRoom){
//		int endRoom = 0;
//		boolean moved = false;
//		int north = currentRoom.getNorth();
//		int east = currentRoom.getEast();
//		int south = currentRoom.getSouth();
//		int west = currentRoom.getWest();
//		while (!moved) {
//			System.out.println("----------");
//			switch (cmdRoom) {
//				case "N", "n", "North", "north" -> {
//					if (north > 0) {
//						endRoom = north - 1;
//						moved = true;
//					} else {
//						System.out.println("There is no way north, try a different direction.");
//					}
//				}
//				case "E", "e", "East", "east" -> {
//					if (east > 0) {
//						endRoom = east - 1;
//						moved = true;
//					} else {
//						System.out.println("There is no way east, try a different direction.");
//					}
//				}
//				case "S", "s", "South", "south" -> {
//					if (south > 0) {
//						endRoom = south - 1;
//						moved = true;
//					} else {
//						System.out.println("There is no way south, try a different direction.");
//					}
//				}
//				case "W", "w", "West", "west" -> {
//					if (west > 0) {
//						endRoom = west - 1;
//						moved = true;
//					} else {
//						System.out.println("There is no way west, try a different direction.");
//					}
//				}
//				default -> System.out.println("Please try one of the four cardinal directions or exit to quit.");
//			}
//			System.out.println("----------");
//		}
//		return "You are now in" get.r;
//	}

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

        switch (itemCmd) {
            case 'G':
                return get();
            case 'R':
                return remove();
            case 'I':
                return inspect();
            default:
                throw new GameException("Invalid command.");
        }
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
    private String get() throws GameException {
        int currentRoom = player.getCurRoom();
//        Item item = currentRoom.getItem();
//
//        if (item == null) {
//            throw new GameException("There is no item to pick up in this room.");
//        }
//
//        // Remove the item from the room
////        currentRoom.removeItem();
//
//        // Add the item to the player's inventory
//        player.addItem(item);

        return "You picked up " + "item.getItemName()" + ".";
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
    private String remove() throws GameException {
        Item item = player.getCurrentItem();

        if (item == null) {
            throw new GameException("You don't have any item to drop.");
        }

        // Remove the item from the player's inventory
        player.removeItem(item);

        // Add the item to the current room
//        player.getCurRoom().addItem(item);

        return "You dropped " + item.getItemName() + ".";
    }

    /**
     * Method look
     * Returns the String for looking at the current room.
     *
     * @return String - the description of the current room
     */
    private String look() {
        int currentRoom = player.getCurRoom();
//        return currentRoom.getDescription();
        return "Room description";
    }

    /**
     * Method backpack
     * Returns the String for displaying the items in the player's backpack.
     *
     * @return String - the items in the backpack
     */
    private String backpack() {
        List<Item> inventory = (List<Item>) player.getInventory();

        if (inventory.isEmpty()) {
            return "Your backpack is empty.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Items in your backpack: ");

            for (Item item : inventory) {
                sb.append(item.getItemName()).append(", ");
            }

            String result = sb.toString();
            return result.substring(0, result.length() - 2); // Remove the trailing comma and space
        }
    }

    /**
     * Method inspect
     * Returns the String for inspecting the current item in the room.
     *
     * @return String - the description of the current item or a message if there is no item
     */
    private String inspect() {
        int currentRoom = player.getCurRoom();
//        Item item = currentRoom.getItem();

//        if (item == null) {
//            return "There is no item to inspect in this room.";
//        } else {
//            return item.getItemDescription();
//        }
        return "Items in room: ";
    }
}

