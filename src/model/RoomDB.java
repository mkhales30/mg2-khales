package model;

import controller.Room;
import gameExceptions.GameException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Class: RoomDB
 *
 * @version 1.2
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * This class holds the Room data for mini game 2. Contains an ArrayList of Room objects.
 * This reads information from ItemDB when retrieving a Room.
 * This allows other classes to request these items.
 */
public class RoomDB {
    private ArrayList<Room> roomArray = new ArrayList<>();
    private static RoomDB instance;

    /**
     * Method getInstance
     * This is a static method to implement the singleton pattern. This allows other objects to get a reference
     * to this class and ensure that they have the same reference. This is required since we are storing the Rooms in
     * an ArrayList and not in a database. This ensures that we have the correct persistence objects.
     * <p>
     * NOTE: For your project and for mini3, if we do mini3, a Singleton is not needed or desired.
     * The singleton is used to save the in-memory data for the game.
     * <p>
     * If the instance is null, the private constructor is called. If it is not null, it simply returns the instance.
     *
     * @return RoomDB - the current RoomDB object.
     */
    public static RoomDB getInstance() throws GameException {
        if (instance == null) {
            instance = new RoomDB();
        }
        return instance;
    }

    /**
     * Private RoomDB constructor
     * This is private to implement the singleton pattern. The class can only be instantiated in this class.
     */
    private RoomDB() throws GameException {
        readRooms();
    }

    /**
     * Method readRooms
     * Reads the Rooms text file to build the rooms.
     * Throws an exception if the text file is not found.
     */
    public void readRooms() throws GameException {
        Scanner in = null;
        try {
            in = new Scanner(new File("rooms.txt"));
        } catch (FileNotFoundException e) {
            throw new GameException("File not found");
        }

        while (in.hasNext()) {
            String name = in.nextLine();
            int id = Integer.parseInt(in.nextLine());
            String desc = in.nextLine();
            int north = Integer.parseInt(in.nextLine());
            int east = Integer.parseInt(in.nextLine());
            int south = Integer.parseInt(in.nextLine());
            int west = Integer.parseInt(in.nextLine());
            boolean visited = Boolean.parseBoolean(in.nextLine());

            Room room = new Room();
            room.setRoomID(id);
            room.setName(name);
            room.setDescription(desc);
            room.setVisited(visited);
            room.setNorth(north);
            room.setEast(east);
            room.setSouth(south);
            room.setWest(west);

            // Add the room to the collection
            roomArray.add(room);
        }

        in.close();
    }

    public ArrayList<Room> getRoomArray() {
        return roomArray;
    }

    /**
     * Method getRoom
     * Returns the Room with the requested ID.
     * If not found, throws a GameException.
     *
     * @param roomID - the int for the room requested.
     * @return Room - the requested room.
     * @throws GameException if the roomID cannot be found.
     */
    public Room getRoom(int roomID) throws GameException {
        //TESTING
//		for (Room roomList : rooms) {
//			for (Room room : roomList) {
//				if (room.getRoomID() == roomID) {
//					return room;
//				}
//			}
//		}
        throw new GameException("Room not found with ID: " + roomID);
    }

    /**
     * Method getItems
     * Returns the list of items in a room.
     * Throws an exception if the room ID is not found.
     *
     * @param roomID - the ID of the room requested.
     * @return ArrayList - the items contained in a room.
     * @throws GameException if the roomID cannot be found.
     */
    public ArrayList<Integer> getItems(int roomID) throws GameException {
        Room room = getRoom(roomID);
        return room.getItems();
    }

    /**
     * Method getMap
     * Returns the String of the complete map.
     *
     * @return String - the complete map.
     */
    public String getMap() throws GameException {
        throw new GameException("Method not implemented yet.");
    }

    /**
     * Method updateRoom
     * Updates the room in the current map.
     * Throws an exception if the room is not found.
     *
     * @param rm - The Room that is being updated.
     */
    public void updateRoom(Room rm) throws GameException {
        throw new GameException("Method not implemented yet.");
    }
}

