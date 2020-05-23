package test;
import classes.Crop;
import classes.Item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CropTest {

	/**
	 * Test for the Getter and Setter of Crops name
	 */
	@Test
	void testName() {
		Crop testCrop = new Crop();
		testCrop.setName("Carrot");
		assertEquals("Carrot", testCrop.getName());
		testCrop.setName("Wheat");
		assertEquals("Wheat", testCrop.getName());
	}

	/**
	 * Test for Getter and Setter of Crops purchase price
	 */
	@Test
	void testPurchasePrice() {
		Crop testCrop = new Crop();
		testCrop.setPurchasePrice(30.0);
		assertEquals(30.0, testCrop.getPurchasePrice());
		testCrop.setPurchasePrice(12.1);
		assertEquals(12.1, testCrop.getPurchasePrice());
	}

	/**
	 * Test for Getter and Setter of Crops sell price
	 */
	@Test
	void testSellPrice() {
		Crop testCrop = new Crop();
		testCrop.setSellPrice(30.0);
		assertEquals(30.0, testCrop.getSellPrice());
		testCrop.setSellPrice(12.1);
		assertEquals(12.1, testCrop.getSellPrice());
	}

	/**
	 * Test for Getter and Setter of crops harvest time
	 */
	@Test
	void testGetHarvestTime() {
		Crop testCrop = new Crop();
		testCrop.setHarvestTime(2);
		assertEquals(2, testCrop.getHarvestTime());
		testCrop.setHarvestTime(1);
		assertEquals(1, testCrop.getHarvestTime());
	}
	
	/**
	 * Test for the Tend method of crops
	 */
	@Test
	void testTend() {
		Crop testCrop = new Crop();
		Item testItem = new Item();
		testItem.setHarvestDecValue(3);
		testCrop.setHarvestTime(4);
		testCrop.tend(testItem);
		assertEquals(testCrop.getHarvestTime(), 1);
		testCrop.tend(testItem);
		assertEquals(testCrop.getHarvestTime(), 0);
	}

}
