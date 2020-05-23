package test;
import classes.Crop;
import classes.CropList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CropListTest {

	
	
	/**
	 * Test for Getters and Setters of Crop List
	 */
	@Test
	void testCropList() {
		CropList testCropList = new CropList();
		ArrayList<Crop> testList = new ArrayList<Crop>();
		Crop testCrop = new Crop();
		testList.add(testCrop);
		testCropList.setCropList(testList);
		assertEquals(testCropList.getCropList(), testList);
	}
	
	
	
	
	/**
	 * Test for AddCrop method of CropList
	 */
	@Test
	void testAddCrop() {
		CropList testCropList = new CropList();
		Crop testCrop = new Crop();
		Crop testCrop2 = new Crop();
		testCrop.setName("Carrot");
		testCrop2.setName("Wheat");
		testCropList.addCrop(testCrop);
		assertEquals("Carrot", testCropList.getCropList().get(0).getName());
		testCropList.addCrop(testCrop2);
		assertEquals(testCrop2, testCropList.getCropList().get(1));
		
	}

	
	/**
	 * Test for getCropCount method of CropList
	 */
	@Test
	void testGetCropCount() {
		CropList testCropList = new CropList();
		Crop testCrop = new Crop();
		Crop testCrop2 = new Crop();
		assertEquals(0, testCropList.getCropCount());
		testCropList.addCrop(testCrop);
		assertEquals(1, testCropList.getCropCount());
		testCropList.addCrop(testCrop2);
		assertEquals(2, testCropList.getCropCount());
	}

	
	/**
	 * Test for the getEachCropCount method of CropList
	 */
	@Test
	void testGetEachCropCount() {
		CropList testCropList = new CropList();
		Crop testCrop = new Crop();
		testCrop.setName("Carrot");
		Crop testCrop2 = new Crop();
		testCrop2.setName("Tomato");
		testCropList.addCrop(testCrop);
		assertEquals(1, testCropList.getEachCropCount().get(0));
		testCropList.addCrop(testCrop);
		assertEquals(2, testCropList.getEachCropCount().get(0));
		testCropList.addCrop(testCrop2);
		assertEquals(1, testCropList.getEachCropCount().get(1));
		
	}
	
	
	/*
	 * Tests harvestGrownCrops methods for CropList
	 */
	@Test
	void testHarvestGrownCrops() {
		CropList testCropList = new CropList();
		Crop testCrop = new Crop();
		Crop testCrop2 = new Crop();
		Crop testCrop3 = new Crop();
		testCrop.setSellPrice(43.0);
		testCrop2.setSellPrice(75.0);
		testCrop3.setSellPrice(10.0);
		testCrop.setHarvestTime(0);
		testCrop2.setHarvestTime(10);
		testCropList.addCrop(testCrop);
		testCropList.addCrop(testCrop2);
		assertEquals(43.0, testCropList.harvestGrownCrops());
		assertEquals(0, testCropList.harvestGrownCrops());
		testCropList.addCrop(testCrop);
		testCrop2.setHarvestTime(0);
		assertEquals(118.0, testCropList.harvestGrownCrops());
		assertEquals(testCropList.getCropCount(), 0);
		testCropList.addCrop(testCrop);
		testCropList.addCrop(testCrop2);
		testCropList.addCrop(testCrop3);
		testCrop.setHarvestTime(0);
		testCrop2.setHarvestTime(10);
		testCrop3.setHarvestTime(0);
		assertEquals(53.0, testCropList.harvestGrownCrops());
		
		
		
		
	}


	/**
	 * Tests the getUniqueCropStrings method of CropList
	 */
	@Test
	void testGetUniqueCropStrings() {
		CropList testCropList = new CropList();
		Crop testCrop = new Crop();
		Crop testCrop2 = new Crop();
		Crop testCrop3 = new Crop();
		testCrop.setName("avocado");
		testCrop2.setName("jelly");
		testCrop3.setName("avocado");
		ArrayList<String> expected = new ArrayList<String>();
		assertEquals(expected, testCropList.getUniqueCropStrings());
		expected.add("avocado");
		expected.add("jelly");
		testCropList.addCrop(testCrop);
		testCropList.addCrop(testCrop2);
		testCropList.addCrop(testCrop3);
		assertEquals(expected, testCropList.getUniqueCropStrings());
	}

	/**
	 * Test the harvestAmount method of CropList
	 */
	@Test
	void testHarvestAmount() {
		CropList testCropList = new CropList();
		Crop testCrop1 = new Crop();
		Crop testCrop2 = new Crop();
		Crop testCrop3 = new Crop();
		testCrop1.setHarvestTime(0);
		testCrop2.setHarvestTime(15);
		testCrop3.setHarvestTime(0);
		testCropList.addCrop(testCrop1);
		testCropList.addCrop(testCrop2);
		testCropList.addCrop(testCrop3);
		assertEquals(2, testCropList.harvestAmount());
		}
}
