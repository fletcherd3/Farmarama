package classes;

import java.util.ArrayList;

public class InventoryList {

    private ArrayList<Item> inventory  = new ArrayList<Item>();


    /**
     * Getter for Inventory
     * @return ArrayList - Item
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Setter for Inventory
     * @param inventory
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * Adds given Item to inventory
     * @param newItem
     */
    public void addItem(Item newItem) {
        inventory.add(newItem);
    }


    /**
     * Returns an integer arraylist of quantities of each item
     * that corespond to the order return by this.getUniqueItems();
     * @return - ArrayList of Integer
     */
    public ArrayList<Integer> getEachItemCounts() {
        ArrayList<Integer> itemCounts = new ArrayList<Integer>();
        ArrayList<String> uniqueItems = this.getUniqueItemStrings();
        Integer index = 0;
        for ( String uItem: uniqueItems) {
            itemCounts.add(0);
            for (Item item: inventory) {
                if (uItem == item.getName()) {
                    Integer currCount = itemCounts.get(index);
                    itemCounts.set(index, currCount + 1);
                }
            }
            index ++;

        }
        return itemCounts;
    }


    /**
     * Returns an ArrayList of strings containing all unique owned items
     * @return - ArrayList of Strings
     */
    public ArrayList<String> getUniqueItemStrings() {
        ArrayList<String> result = new ArrayList<String>();
        for (Item item: inventory) {
            if (!result.contains(item.getName())) {
                result.add(item.getName());
            }
        }
        return result;
    }



    /**
     * Given an String, will return an item in inventory that has the
     * same name.
     * @param itemName - String
     * @return - Item
     */
    public Item getItemFromString(String itemName) {
        Boolean itemFound = false;
        Item result = null;
        for (Item item : inventory) {
            if (!itemFound) {
                if (item.getName() == itemName) {
                    itemFound = true;
                    result = item;

                }
            }
        }
        return result;
    }



}
