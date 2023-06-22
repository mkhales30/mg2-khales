package controller;


import gameExceptions.GameException;
import model.RoomDB;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Room currentRoom;
    private ArrayList<Room> rooms;
    private Scanner input;
    private boolean escape = false;
    private RoomDB rdb;

    public Game() throws GameException {
        rdb = RoomDB.getInstance();
        rooms = rdb.getRoomArray();
        input = new Scanner(System.in);
    }

    public void play() throws FileNotFoundException {
        currentRoom = rooms.get(0);

        //added
//        RoomList rl = new RoomList();

//        System.out.println("Welcome to the game!");
//        System.out.println("----------");

        //added
        System.out.println("Room Info:");
        for (Room room : rooms) {
            System.out.println(room.getName() + " - " + room.getDescription());
        }
        System.out.println("----------");

//        System.out.println("You are currently in the " + currentRoom.getName() + ".");
//        System.out.println(currentRoom.getDescription());


        while (!escape) {
            enterRoom(currentRoom);
            currentRoom = rooms.get(move());
        }
    }

    private void enterRoom(Room thisRoom) {
        int waysOut = 0;
        String directions = "You can leave to the ";
        boolean north = false;
        boolean east = false;
        boolean south = false;
        boolean west = false;
        System.out.println("You are in " + thisRoom.getName());
        System.out.println("a " + thisRoom.getDescription());
        if (thisRoom.isVisited()) {
            System.out.println("You have been here before");
        } else {
            System.out.println("You have never been here before.");
        }

        currentRoom.visitedRoom();

        if (thisRoom.getNorth() > 0) {
            north = true;
            ++waysOut;
        }
        if (thisRoom.getEast() > 0) {
            east = true;
            ++waysOut;
        }
        if (thisRoom.getSouth() > 0) {
            south = true;
            ++waysOut;
        }
        if (thisRoom.getWest() > 0) {
            west = true;
            ++waysOut;
        }
        if (north) {
            directions += "north";
            --waysOut;
            if (waysOut > 1) {
                directions += ", ";
            } else if (waysOut == 1) {
                directions += " and ";
            }
        }
        if (east) {
            directions += "east";
            --waysOut;
            if (waysOut > 1) {
                directions += ", ";
            } else if (waysOut == 1) {
                directions += " and ";
            }
        }
        if (south) {
            directions += "south";
            --waysOut;
            if (waysOut == 1) {
                directions += " and ";
            }
        }
        if (west) {
            directions += "west";
        }
        System.out.println(directions + ".");
    }

    private int move() {
        int endRoom = 0;
        boolean moved = false;
        int north = currentRoom.getNorth();
        int east = currentRoom.getEast();
        int south = currentRoom.getSouth();
        int west = currentRoom.getWest();
        while (!moved) {
            System.out.println("----------");
            switch (input.nextLine()) {
                case "N", "n", "North", "north" -> {
                    if (north > 0) {
                        endRoom = north - 1;
                        moved = true;
                    } else {
                        System.out.println("There is no way north, try a different direction.");
                    }
                }
                case "E", "e", "East", "east" -> {
                    if (east > 0) {
                        endRoom = east - 1;
                        moved = true;
                    } else {
                        System.out.println("There is no way east, try a different direction.");
                    }
                }
                case "S", "s", "South", "south" -> {
                    if (south > 0) {
                        endRoom = south - 1;
                        moved = true;
                    } else {
                        System.out.println("There is no way south, try a different direction.");
                    }
                }
                case "W", "w", "West", "west" -> {
                    if (west > 0) {
                        endRoom = west - 1;
                        moved = true;
                    } else {
                        System.out.println("There is no way west, try a different direction.");
                    }
                }
                case "Exit", "exit", "X", "x" -> {
                    escape = true;
                    moved = true;
                    endRoom = currentRoom.getRoomID() - 1;
                }
                default -> System.out.println("Please try one of the four cardinal directions or exit to quit.");
            }
            System.out.println("----------");
        }
        return endRoom;
    }
}
