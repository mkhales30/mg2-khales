package model;

import controller.Item;
import controller.Room;
import gameExceptions.GameException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class: RoomDB
 * @version 1.2
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * This class   Holds the Room data for mini game 2. Contains an ArrayList Room
 * This reads information from ItemDB when retrieving a Room.
 * This allows the other classes to request these items.
 */
public class RoomDB {

	private Collection<ArrayList<Room>> rooms;
	private static RoomDB instance;

	/**
	 * Method getInstance
	 * This is a static method to implement the singleton pattern. This allows the other objects to get a reference
	 * to this class and ensure that they have the same reference. This is required since we are storing the Rooms in
	 * ArrayList and not in a database. THis ensures that we have the correct persistence objects.
	 * 
	 * NOTE: for your project and for mini3, if we do mini3, a Singleton is not needed or desired.
	 * The singleton is to save the in memory data for the game
	 * 
	 * If instance is null, the private constructor is called. If it is not, it simply returns the instance
	 * @return RoomDB - the current RoomDB object.
	 */
	public static RoomDB getInstance() throws GameException {
		return instance;
	}

	/**
	 * private RoomDB constructor
	 * This is private to implement the singleton pattern. Class can only be instantiated in this class.
	 */
	private RoomDB() throws GameException {
		// TODO - implement RoomDB.RoomDB
		throw new UnsupportedOperationException();
	}

	/**
	 * Method readRooms
	 * Reads the Rooms text file to build the rooms
	 * throws an exception if the text file not found
	 */
	public void readRooms() throws GameException {
		// TODO - implement RoomDB.readRooms
		throw new UnsupportedOperationException();
	}

	/**
	 * Method getRoom
	 * returns the Room with the ID requested
	 * If not found, throws a GameException
	 * @param roomID - the int for the room requested
	 * @return Room - the requested room
	 * @throws GameException if the roomID cannot be found
	 */
	public Room getRoom(int roomID) throws GameException {
		// TODO - implement RoomDB.getRoom
		throw new UnsupportedOperationException();
	}

	/**
	 * Method getItems
	 * Returns the list of items in a room
	 * throws an exception if the room ID is not found
	 * @param roomID - the id of the room requested
	 * @return ArrayList -  the items contained in a room
	 * @throws GameException if the roomID cannot be found
	 */
	public ArrayList<Item> getItems(int roomID) throws GameException {
		// TODO - implement RoomDB.getItems
		throw new UnsupportedOperationException();
	}

	/**
	 * Method getMap
	 * Returns the String of the complete map
	 * @return String
	 */
	public String getMap() {
		// TODO - implement RoomDB.getMap
		throw new UnsupportedOperationException();
	}

	/**
	 * Method updateRoom
	 * Updates the room in the current map
	 * throws an exception if the room is not found
	 * @param rm - The Room that is being updated
	 */
	public void updateRoom(Room rm) throws GameException {
		// TODO - implement RoomDB.updateRoom
		throw new UnsupportedOperationException();
	}

}