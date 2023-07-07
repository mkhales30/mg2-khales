package controller;

import gameExceptions.GameException;

/**
 * Class: Exit
 *
 * @version 1.2
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * <p>
 * This class contains the Exit information. It allows the user to build an
 * exit to be added to the Room.
 */
public class Exit {

    private String direction;
    private int destination;

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getDestination() {
        return this.destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Exit [direction=" + direction + ", destination=" + destination + "]";
    }

    /**
     * Method Exit
     * Builds an Exit from the String provided.
     * Throws an exception for an invalid exit.
     * You can replace this with a constructor that takes a String and parses into the Exit.
     *
     * @throws GameException
     */
    public Exit(String direction, int destination) throws GameException {
        this.direction = direction;// need to check if valid
        this.destination = destination;
    }

}
