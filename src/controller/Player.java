package controller;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class: Player
 * @version 1.2
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * 
 * This class  handles the Player object. This is concerned with tracking
 * inventory and current room
 * for this implementation
 */
class Player {

	private int curRoom;
	private Collection<ArrayList<Item>> inventory;

	public int getCurRoom() {
		return this.curRoom;
	}

	public void setCurRoom(int curRoom) {
		this.curRoom = curRoom;
	}

	public Collection<ArrayList<Item>> getInventory() {
		return this.inventory;
	}

	public void setInventory(Collection<ArrayList<Item>> inventory) {
		this.inventory = inventory;
	}

	/**
	 * Method Player
	 * Constructor for the Player class
	 * Instatiates the ArrayList to hold the Inventory
	 */
	protected Player() {
		// TODO - implement Player.Player
		throw new UnsupportedOperationException();
	}

	/**
	 * Method addItem
	 * Adds an item to the player's inventory
	 * @param it - the Item to add to the inventory
	 */
	protected void addItem(Item it) {
		// TODO - implement Player.addItem
		throw new UnsupportedOperationException();
	}

	/**
	 * Method removeItem
	 * Removes an item from the player's inventory
	 * @param it - the Item to remove from the inventory
	 */
	protected void removeItem(Item it) {
		// TODO - implement Player.removeItem
		throw new UnsupportedOperationException();
	}

	/**
	 * Method printInventory
	 * Returns the String of all items in the player's inventory
	 * @return String - the String of the player's inventory
	 */
	protected String printInventory() {
		// TODO - implement Player.printInventory
		throw new UnsupportedOperationException();
	}

}