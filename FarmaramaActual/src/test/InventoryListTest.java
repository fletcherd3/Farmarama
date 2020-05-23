package test;

import classes.InventoryList;
import classes.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryListTest {


    /**
     * Tests the Getter and Setter of InventoryList
     */
    @Test
    void testInventory() {
        InventoryList testInv = new InventoryList();
        ArrayList<Item> testArray = new ArrayList<Item>();
        Item testItem = new Item();
        testArray.add(testItem);
        testInv.setInventory(testArray);
        assertEquals(testInv.getInventory(), testArray);

    }


    /**
     * Tests the addItem method of InventoryList
     */
    @Test
    void addItem() {
        InventoryList testInv = new InventoryList();
        Item testItem = new Item();
        ArrayList<Item> testArray = new ArrayList<Item>();
        testArray.add(testItem);
        testInv.addItem(testItem);
        assertEquals(testArray, testInv.getInventory());

    }


    /**
     * Tests the getEachItemCount method of InventoryList
     */
    @Test
    void getEachItemCounts() {
        InventoryList testInv = new InventoryList();
        ArrayList<Integer> testArray = new ArrayList<Integer>();
        assertEquals(testArray, testInv.getEachItemCounts());
        Item testItem = new Item();
        Item testItem2 = new Item();
        Item testItem3 = new Item();
        testItem.setName("First");
        testItem2.setName("Second");
        testItem3.setName("Third");
        testInv.addItem(testItem);
        testArray.add(1);
        assertEquals(testArray, testInv.getEachItemCounts());
        testInv.addItem(testItem2);
        testArray.add(1);
        assertEquals(testArray, testInv.getEachItemCounts());
        testInv.addItem(testItem);
        testArray.set(0, 2);
        assertEquals(testArray, testInv.getEachItemCounts());
        testArray.add(1);
        testInv.addItem(testItem3);
        assertEquals(testArray, testInv.getEachItemCounts());
    }

    /**
     * Test for the getUniqueItemStrings method of InventoryList
     */
    @Test
    void testGetUniqueItemStrings() {
        InventoryList testInv = new InventoryList();
        ArrayList<String> testArray = new ArrayList<String>();
        assertEquals(testArray, testInv.getUniqueItemStrings());
        Item testItem = new Item();
        Item testItem2 = new Item();
        Item testItem3 = new Item();
        testItem.setName("First");
        testItem2.setName("Second");
        testItem3.setName("Third");
        testInv.addItem(testItem);
        testArray.add("First");
        assertEquals(testArray, testInv.getUniqueItemStrings());
        testInv.addItem(testItem2);
        testArray.add("Second");
        assertEquals(testArray, testInv.getUniqueItemStrings());
        testInv.addItem(testItem);
        assertEquals(testArray, testInv.getUniqueItemStrings());
        testArray.add("Third");
        testInv.addItem(testItem3);
        assertEquals(testArray, testInv.getUniqueItemStrings());
    }


    /**
     * Test the getItemFromString method of InventoryList
     */
    @Test
    void testGetItemFromString() {
        InventoryList testInv = new InventoryList();
        Item testItem = new Item();
        testItem.setName("Carrot");
        testInv.addItem(testItem);
        assertEquals(testItem, testInv.getItemFromString("Carrot"));
        Item testItem2 = new Item();
        testItem2.setName("potato");
        testInv.addItem(testItem2);
        assertEquals(testItem2, testInv.getItemFromString("potato"));

    }
}