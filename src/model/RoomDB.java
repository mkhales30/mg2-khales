package model;

import controller.Exit;
import controller.Room;
import gameExceptions.GameException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class: RoomDB
 *
 * @version 1.2
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * This class holds the Room data for mini-game 2. Contains an ArrayList of Room objects.
 * This reads information from ItemDB when retrieving a Room.
 * This allows other classes to request these items.
 */
public class RoomDB {
    private final ArrayList<Room> roomArray = new ArrayList<>();
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
        try{
        Scanner in;
        try {
            in = new Scanner(new File("rooms.txt"));
        } catch (FileNotFoundException e) {
            throw new GameException("File not found");
        }

        String separator = "----";

        // Adding data scanned to arraylist
        while (in.hasNextLine()){
            int roomID = Integer.parseInt(in.nextLine()); //ID
            String name = in.nextLine(); //name

            // Scanning room description
            StringBuilder stringBuilder = new StringBuilder();
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.equals(separator)) {
                    break;
                }
                stringBuilder.append(line).append("\n");
            }
            String description = stringBuilder.toString(); //description

            // Scanning room exits
            ArrayList<Exit> exits = new ArrayList<>();
            String exitDirection;
            int exitNumber;

            while (in.hasNextLine()) {
                String line = in.next();
                if (line.equals(separator)) {
                    break;
                }
                exitDirection = line;
                exitNumber = in.nextInt();
                exits.add(new Exit(exitDirection, exitNumber));
            }

            ArrayList<Integer> itemsID = new ArrayList<>();
            while (in.hasNextLine()) {
                String line = in.next();
                if (line.equals(separator)) {
                    break;
                }

                int id = Integer.parseInt(line);
                itemsID.add(id);

            }

            Room newRoom = new Room(roomID, name, description, exits, itemsID); //creates and populates new room
            roomArray.add(newRoom);

            if(in.hasNextLine()){
                in.nextLine();
            }
        }
        in.close();
        } catch (GameException e) {
            throw new GameException(e.getMessage());
        }
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
        for (Room room: roomArray) {
            if (room.getRoomID() == roomID) {
                return room;
            }
        }
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
//    public ArrayList<Integer> getItems(int roomID) throws GameException {
//        Room room = getRoom(roomID);
//        return room.getItems();
//    }

    /**
     * Method getMap
     * Returns the String of the complete map.
     *
     * @return String - the complete map.
     */
    public String getMap() throws GameException {
        throw new GameException("Method not implemented yet.");
    }

}

