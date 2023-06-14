package controller;

import gameExceptions.GameException;

import java.util.Arrays;
import java.util.List;

/**
 * Class: Exit
 * @version 1.2
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * 
 * This class  contains the Exit information. Allows the user to build an
 * exit to be added to the Room
 */
public class Exit {

	private String direction;
	private int destination;
	private final List<String> VALID_DIRECTIONS = Arrays.asList(new String[] {"WEST", "NORTH", "SOUTH", "EAST", "UP", "DOWN"});

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

	@Override()
	public String toString() {
		// TODO - implement Exit.toString
		throw new UnsupportedOperationException();
	}

	/**
	 * Method buildExit
	 * Builds an Exit from the String provided
	 * throws an exception for an invalid exit
	 * You can replace this with a constructor that takes a String and parses into the Exit.
	 * @param ex - String containing the information for the exit
	 */
	public void buildExit(String ex) throws GameException {
		// TODO - implement Exit.buildExit
		throw new UnsupportedOperationException();
	}

}