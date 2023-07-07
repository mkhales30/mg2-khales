package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class: Player
 *
 * @version 1.2
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * <p>
 * This class handles the Player object. It is concerned with tracking
 * inventory and the current room.
 */
class Player {

    private int curRoom;
    private List<Item> inventory;

    public int getCurRoom() {
        return this.curRoom;
    }

    public void setCurRoom(int curRoom) {
        this.curRoom = curRoom;
    }

    public Collection<Item> getInventory() {
        return this.inventory;
    }

    public void setInventory(Collection<Item> inventory) {
        this.inventory = (List<Item>) inventory;
    }

    /**
     * Method Player
     * Constructor for the Player class
     * Instantiates the ArrayList to hold the Inventory
     */
    protected Player() {
        inventory = new ArrayList<>();
    }

    /**
     * Method addItem
     * Adds an item to the player's inventory
     *
     * @param it - the Item to add to the inventory
     */
    protected void addItem(Item it) {
        inventory.add(it);
    }

    /**
     * Method removeItem
     * Removes an item from the player's inventory
     *
     * @param it - the Item to remove from the inventory
     */
    protected void removeItem(Item it) {
        inventory.remove(it);
    }

    /**
     * Method printInventory
     * Returns the String of all items in the player's inventory
     *
     * @return String - the String of the player's inventory
     */
    protected String printInventory() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory:");
        for (Item item : inventory) {
            sb.append("\n").append("- ").append(item.getItemName());
        }
        return sb.toString();
    }
}
