package test;
import classes.Item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemTest {

	/**
	 * Test for Getter and Setter of Item name
	 */
	@Test
	void testName() {
		Item testItem = new Item();
		testItem.setName("Water");
		assertEquals("Water", testItem.getName());
		testItem.setName("Fertilizer");
		assertEquals("Fertilizer", testItem.getName());
	}

	
	/**
	 * Test for Getter and Setter of Item purchase price
	 */
	@Test
	void testPurchasePrice() {
		Item testItem = new Item();
		testItem.setPurchasePrice(45.1);
		assertEquals(45.1, testItem.getPurchasePrice());
		testItem.setPurchasePrice(2.1);
		assertEquals(2.1, testItem.getPurchasePrice());
				
	}

	
	/**
	 * Test for Getter and Setter of Item Health Value
	 */
	@Test
	void testHealthValue() {
		Item testItem = new Item();
		testItem.setHealthValue(23);
		assertEquals(testItem.getHealthValue(), 23);
		testItem.setHealthValue(4);
		assertEquals(testItem.getHealthValue(), 4);
	}

	
	/**
	 * Test for Getter and Setter of Item HarvestDecValue
	 */
	@Test
	void testGetHarvestDecValue() {
		Item testItem = new Item();
		testItem.setHarvestDecValue(77);
		assertEquals(77, testItem.getHarvestDecValue());
		testItem.setHarvestDecValue(7);
		assertEquals(7, testItem.getHarvestDecValue());
	}

}
