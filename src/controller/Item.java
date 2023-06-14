package controller;

/**
 * Class: Item
 * * @version 1.2
 *  * Course: ITEC 3860 Spring 2023
 *  * Written: February 18, 2023
 * This class  Handles Items in the game.
 */
public class Item {

	private int itemID;
	private String itemName;
	private String itemDescription;

	public int getItemID() {
		return this.itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return this.itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * Method display
	 * This method returns the itemDescription which is the String that will be displayed in the game
	 * @return the String to display in the game
	 */
	public String display() {
		// TODO - implement Item.display
		throw new UnsupportedOperationException();
	}

	@Override()
	public String toString() {
		// TODO - implement Item.toString
		throw new UnsupportedOperationException();
	}

}