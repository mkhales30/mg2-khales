package controller;

import gameExceptions.GameException;
import model.ItemDB;
import model.RoomDB;

import java.util.ArrayList;

/**
 * Class: Room
 *
 * @version 1.2
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * <p>
 * This class handles the Room interactions. It contains the roomID,
 * name, description, items, visited flag, exits, and references to RoomDB and ItemDB.
 */
public class Room {

    private int roomID;
    private String name;
    private String description;
    private final ArrayList<Item> items;

    private ArrayList<Exit> exits;
    private RoomDB rdb;

    private boolean visited = false;

    public void visitedRoom() {
        this.visited = true;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ArrayList<Exit> getExits() {
        return this.exits;
    }

    public void setExits(ArrayList<Exit> exits) {
        this.exits = exits;
    }

    /**
     * Method Room
     * Constructor for the Room class
     * Initializes exits and items ArrayLists and gets the current map.
     */
    public Room() throws GameException {
        rdb = RoomDB.getInstance();
        ItemDB.getInstance();
        exits = new ArrayList<>();
        items = new ArrayList<>();
    }

    /**
     * Room constructor
     * constructs the room object with the given ID
     *
     * @param id
     */


    /**
     * Method Room
     * Constructor for the Room class
     * Initializes exits and items ArrayLists
     */
    public Room(int roomID, String name, String description, ArrayList<Exit> roomExits, ArrayList<Integer> itemsID) throws GameException {
        this.roomID = roomID;
        this.name = name;
        this.description = description;

        this.exits = roomExits;

        items = new ArrayList<>();
        buildItems(itemsID);


        visited = false;
    }

    /**
     * Method display
     * Builds a String representation of the current room
     * Calls buildItems, buildDescription, and displayExits to build this String
     *
     * @return String - the current room display String
     * @throws GameException if the Item String cannot be built
     */
    public String display() throws GameException {
        return name + "- " + description + "\nItems in this room: " + items;
    }

    /**
     * Method buildItems
     * Builds a String of the items in the room
     *
     * @throws GameException if the list of items cannot be built
     */
    private void buildItems(ArrayList<Integer> itemsID) throws GameException {
        try {
            ItemDB itemDB;

            itemDB = ItemDB.getInstance();
            itemDB.readItems();

//            StringBuilder sb = new StringBuilder();
//            sb.append("Items in the room:\n");

            for (Integer itemID : itemsID) {
                items.add(itemDB.getItem(itemID));
//            sb.append("- ").append(item.getItemName()).append("\n");
            }
        } catch (GameException e) {
            throw new GameException(e.getMessage());
        }
    }

    /**
     * Method removeItem
     * Removes an item from the room. Removes it and calls updateRoom to save the changes
     *
     * @param item - the Item to remove
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Method dropItem
     * Adds an item to the room. Adds it and calls updateRoom to save the changes
     *
     * @param item - the Item to add
     */
    public void dropItem(Item item) {
        items.add(item);
    }

    /**
     * Method displayExits
     * Builds a String of the exits in the room
     *
     * @return String - the current room exits text
     */
    private String displayExits() {
        StringBuilder sb = new StringBuilder();
        sb.append("Exits:\n");
        for (Exit exit : exits) {
            sb.append("- ").append(exit.getDestination()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Method retrieveByID
     * Retrieves the requested Room from RoomDB. Sets its values into the current Room and returns it
     *
     * @param roomNum ID of the room to retrieve
     * @throws GameException if the room cannot be found
     */
    public void retrieveByID(int roomNum) throws GameException {
        rdb.getRoom(roomNum);
    }

    /**
     * Method validDirection
     * Determines if the direction entered by the user is valid for this room
     * Throws an exception if this is invalid
     *
     * @param cmd - The direction the user wants to move
     * @return int - the ID of the destination room
     * @throws GameException if the direction chosen is not valid
     */
    public int validDirection(char cmd) throws GameException {
        for (int i = 0; i < exits.size(); i++) {
            if (exits.get(i).getDirection().charAt(0) == cmd) {
                return (i);
            }
        }
        throw new GameException("Invalid direction!");
    }

    /**
     * Method: getRoomItems
     * <p>
     * This method calls RoomDB to get the items that are in the current room.
     *
     * @return ArrayList Item - the items in the room
     * @throws GameException if the list of items cannot be built
     */
    public ArrayList<Item> getRoomItems() throws GameException {
        try {
            return items;
        } catch (Exception e) {
            throw new GameException();
        }
    }

    @Override
    public String toString() {
        return name;
    }

}

