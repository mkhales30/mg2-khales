package model;

import controller.Item;
import gameExceptions.GameException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Class: ItemDB
 *
 * @version 1.2
 * Course: ITEC 3860 Spring 2023
 * Written: February 18, 2023
 * <p>
 * This class holds the Item data for mini-game 2. Contains an ArrayList of Item objects.
 * This allows other classes to request these items.
 */
public class ItemDB {

    private Collection<Item> items;
    public static ItemDB instance;

    /**
     * Method getInstance
     * This is a static method to implement the singleton pattern. This allows other objects to get a reference
     * to this class and ensure that they have the same reference. This is required since we are storing the Items in
     * an ArrayList and not in a database. This ensures that we have the correct persistence objects.
     * <p>
     * NOTE: For your project and for mini3, if we do mini3, a Singleton is not needed or desired.
     * The singleton is used to save the in-memory data for the game.
     * <p>
     * If the instance is null, the private constructor is called. If it is not null, it simply returns the instance.
     *
     * @return ItemDB - the current ItemDB object.
     */
    public static ItemDB getInstance() throws GameException {
        if (instance == null) {
            instance = new ItemDB();
        }
        return instance;
    }

    /**
     * Private ItemDB constructor
     * This is private to implement the singleton pattern. The class can only be instantiated in this class.
     */
    private ItemDB() throws GameException {
        items = new ArrayList<Item>();
        readItems();
    }

    /**
     * Method getItem
     * Returns the Item requested by the ID. Only used in readItems.
     *
     * @param id - the ID of the item requested.
     * @return Item - the requested Item.
     * @throws GameException if the item ID cannot be found.
     */
    public Item getItem(int id) throws GameException {
        //TESTING
//		for (Item itemList : items) {
//			for (Item item : itemList) {
//				if (item.getItemID() == id) {
//					return item;
//				}
//			}
//		}
        throw new GameException("Item not found with ID: " + id);
    }

    /**
     * Method readItems
     * Reads the Items text file to build the items.
     * Throws an exception if the text file is not found.
     */
    public void readItems() throws GameException {
        Scanner in = null;
        try {
            in = new Scanner(new File("items.txt"));
        } catch (FileNotFoundException e) {
            throw new GameException("File not found");
        }

        while (in.hasNext()) {

            int id = Integer.parseInt(in.nextLine());
            String name = in.nextLine();
            int roomId = Integer.parseInt(in.nextLine());
            String desc = in.nextLine();

            Item item = new Item();
            item.setItemID(id);
            item.setItemName(name);
            item.setItemDescription(desc);


            // Add the room to the collection
            items.add(item);
        }

        in.close();
    }
}
