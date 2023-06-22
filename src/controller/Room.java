package controller;

import gameExceptions.GameException;
import model.ItemDB;
import model.RoomDB;

import java.util.ArrayList;
import java.util.Collection;

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
    private ArrayList<Integer> items;
    private boolean visited;
    private Collection<Exit> exits;
    private RoomDB rdb;
    private ItemDB idb;

    public void visitedRoom() {
        this.visited = true;
    }

    public int getNorth() {
        return north;
    }

    public void setNorth(int north) {
        this.north = north;
    }

    public int getWest() {
        return west;
    }

    public void setWest(int west) {
        this.west = west;
    }

    public int getSouth() {
        return south;
    }

    public void setSouth(int south) {
        this.south = south;
    }

    public int getEast() {
        return east;
    }

    public void setEast(int east) {
        this.east = east;
    }

    private int north;
    private int west;
    private int south;
    private int east;

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

    public ArrayList<Integer> getItems() {
        return items;
    }

    public void setItems(ArrayList<Integer> items) {
        this.items = items;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Collection<Exit> getExits() {
        return exits;
    }

    public void setExits(Collection<Exit> exits) {
        this.exits = exits;
    }

    /**
     * Method Room
     * Constructor for the Room class
     * Initializes exits and items ArrayLists and gets the current map.
     */
    public Room() throws GameException {
        rdb = RoomDB.getInstance();
        idb = ItemDB.getInstance();
        exits = new ArrayList<>();
        items = new ArrayList<>();
    }

    /**
     * Room constructor
     * constructs the room object with the given ID
     *
     * @param id
     */
    public Room(int id) throws GameException {
        this();
        Room temp = rdb.getRoom(id);
        roomID = temp.getRoomID();
        name = temp.getName();
        description = temp.getDescription();
        items = temp.getItems();
        exits = temp.getExits();
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
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        sb.append(buildDescription()).append("\n");
        sb.append(buildItems()).append("\n");
        sb.append(displayExits()).append("\n");
        return sb.toString();
    }

    /**
     * Method buildDescription
     * Builds a String of the description
     *
     * @return String - the current room description text
     */
    private String buildDescription() {
        return description;
    }

    /**
     * Method buildItems
     * Builds a String of the items in the room
     *
     * @return String - the current room items text
     * @throws GameException if the list of items cannot be built
     */
    private String buildItems() throws GameException {
        StringBuilder sb = new StringBuilder();
        sb.append("Items in the room:\n");
        for (int itemId : items) {
            Item item = idb.getItem(itemId);
            sb.append("- ").append(item.getItemName()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Method removeItem
     * Removes an item from the room. Removes it and calls updateRoom to save the changes
     *
     * @param item - the Item to remove
     */
    public void removeItem(Item item) throws GameException {
        items.remove(Integer.valueOf(item.getItemID()));
        updateRoom();
    }

    /**
     * Method dropItem
     * Adds an item to the room. Adds it and calls updateRoom to save the changes
     *
     * @param item - the Item to add
     */
    public void dropItem(Item item) throws GameException {
        items.add(item.getItemID());
        updateRoom();
    }

    /**
     * Method updateRoom
     * Calls RoomDB updateRoom(this) to save the current room in the map
     */
    public void updateRoom() throws GameException {
        rdb.updateRoom(this);
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
     * @return Room - the requested Room
     * @throws GameException if the room cannot be found
     */
    public Room retrieveByID(int roomNum) throws GameException {
        return rdb.getRoom(roomNum);
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
        for (Exit exit : exits) {
            if (exit.getDirection().equals(cmd)) {
                return exit.getDestination();
            }
        }
        throw new GameException("Invalid direction!");
    }

    /**
     * Method getRoomItems
     * This method calls RoomDB to get the items that are in the current room.
     *
     * @return ArrayList<Item> - the items in the room
     * @throws GameException if the list of items cannot be built
     */
    public ArrayList<Item> getRoomItems() throws GameException {
        ArrayList<Item> roomItems = new ArrayList<>();
        for (int itemId : items) {
            Item item = idb.getItem(itemId);
            roomItems.add(item);
        }
        return roomItems;
    }

    @Override
    public String toString() {
        return name;
    }

}

