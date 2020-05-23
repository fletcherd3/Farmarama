package test;

import classes.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FarmTest {


	/**
	 *  Tests the Getter and Setter of the Farm name
	 */
	@Test
	void nameTest() {
		Farm testFarm = new Farm();
		testFarm.setName("Goggo");
		assertEquals("Goggo", testFarm.getName());
		testFarm.setName("Name2");
		assertEquals("Name2", testFarm.getName());
	}

	
	/**
	 * Test the Getter and Setter of the Farm type, 
	 * and also checks that the correct bonuses are applied
	 */
	@Test
	void typeTest() {
		Farm testFarm = new Farm();
		testFarm.setType("Plain");
		assertEquals("Plain", testFarm.getType());
		assertEquals(8.0, testFarm.getBonuses().get("animalHealth"));
		assertEquals(7.0, testFarm.getBonuses().get("animalHappiness"));
		assertEquals(1.0, testFarm.getBonuses().get("harvestTime"));
		testFarm.setType("Cold");
		assertEquals(6.0, testFarm.getBonuses().get("animalHealth"));
		assertEquals(6.0, testFarm.getBonuses().get("animalHappiness"));
		assertEquals(1.0, testFarm.getBonuses().get("harvestTime"));
		testFarm.setType("Hot");
		assertEquals(8.0, testFarm.getBonuses().get("animalHealth"));
		assertEquals(9.0, testFarm.getBonuses().get("animalHappiness"));
		assertEquals(2.0, testFarm.getBonuses().get("harvestTime"));
		testFarm.setType("Alpine");
		assertEquals(7.0, testFarm.getBonuses().get("animalHealth"));
		assertEquals(6.0, testFarm.getBonuses().get("animalHappiness"));
		assertEquals(2.0, testFarm.getBonuses().get("harvestTime"));
	}
	
	
	/**
	 * Tests the Getter and Setter for the Farm's Farmer
	 */
	@Test
	void setFarmerTest() {
		Farm testFarm = new Farm();
		Farmer testFarmer = new Farmer();
		Farmer testFarmer2 = new Farmer();
		testFarm.setFarmer(testFarmer);
		assertEquals(testFarm.getFarmer(), testFarmer);
		testFarm.setFarmer(testFarmer2);
		assertEquals(testFarm.getFarmer(), testFarmer2);
		assertNotEquals(testFarm.getFarmer(), testFarmer);
		
	}
	
	
	/**
	 * Test the Getter and Setter for the farms balance
	 */
	@Test
	void setBalanceTest() {
		Farm testFarm = new Farm();
		testFarm.setBalance(0.0);
		assertEquals(0.0, testFarm.getBalance());
		testFarm.setBalance(5765.1);
		assertEquals(5765.1, testFarm.getBalance());
	}
	
	
	/**
	 * Tests the modifyBalance() method of the Farm, 
	 * to ensure it behaves as expected outside the
	 * expected boundaries
	 */
	@Test
	void modifyBalanceTest() {
		Farm testFarm = new Farm();
		testFarm.setBalance(50.0);
		testFarm.modifyBalance(10.0);
		assertEquals(testFarm.getBalance(), 60.0);
		testFarm.modifyBalance(-20.0);
		assertEquals(testFarm.getBalance(), 40.0);
		assertEquals(testFarm.modifyBalance(-50.0), false);
		assertEquals(testFarm.getBalance(), 40.0);
		assertEquals(testFarm.modifyBalance(10.0), true);
		
	}
	
	
	/**
	 * Tests the Getter and Setter of the Farms crop limit
	 */
	@Test
	void testSetCropLimit() {
		Farm testFarm = new Farm();
		testFarm.setCropLimit(54);
		assertEquals(testFarm.getCropLimit(), 54);
		testFarm.setCropLimit(-5);
		assertEquals(testFarm.getCropLimit(), 54);
		assertEquals(testFarm.setCropLimit(30), true);
		assertEquals(testFarm.setCropLimit(-3), false);
	}
	
	
	
	/**
	 * Tests the Farms tendToFarm function
	 */
	@Test
	void testTendToFarm() {
		Farm testFarm = new Farm();
		testFarm.setType("Plain");
		testFarm.setCropLimit(50);
		testFarm.tendToFarm();
		assertEquals(testFarm.getCropLimit(), 52);
		assertEquals(8, testFarm.getBonuses().get("animalHappiness"));
		
		
	}
	
	
	/*
	 * Test the Getter and Setter of Farms crops
	 */
	@Test 
	void testCrops() {
		Farm testFarm = new Farm();
		CropList testCropList = new CropList();
		Crop testCrop = new Crop();
		testCropList.addCrop(testCrop);
		testFarm.setCrops(testCropList);
		assertEquals(testCropList, testFarm.getCrops());
		
	}
	
	
	/*
	 * Test for the Getter and Setter of farms animals
	 */
	@Test 
	void testAnimals() {
		Farm testFarm = new Farm();
		AnimalList testAnimalList = new AnimalList();
		Animal testAnimal = new Animal();
		testAnimalList.addAnimal(testAnimal);
		testFarm.setAnimals(testAnimalList);
		assertEquals(testAnimalList, testFarm.getAnimals());
	}


	/**
	 * Tests the growCrops method of Farm
	 */
	@Test
	void testGrowCrops() {
		Farm testFarm = new Farm();
		testFarm.setType("Plain");
		testFarm.getCrops().addCrop(testFarm.createCrop("carrot"));
		testFarm.growCrops();
		assertEquals(testFarm.getCrops().getCropList().get(0).getHarvestTime(), 1);
	}


	/**
	 * Test for the getDailyBonus method of farm
	 */
	@Test
	void testGetDailyBonus() {
		Farm testFarm = new Farm();
		testFarm.setType("Plain");
		testFarm.getAnimals().addAnimal(testFarm.createAnimal("cow"));
		Double balance = testFarm.getBalance();
		testFarm.getDailyBonus();
		assertEquals(testFarm.getBalance(), balance + 25.0 * (testFarm.getAnimals().getAnimalList().get(0).getHappiness() / 100.0) *
				(testFarm.getAnimals().getAnimalList().get(0).getHealth() / 100.0));

	}

	/**
	 * Test for the decreaseAnimalWellbeing method of farm
	 */
	@Test
	void testDecreaseAnimalWellbeing() {
		Farm testFarm = new Farm();
		testFarm.setType("Plain");
		testFarm.getAnimals().addAnimal(testFarm.createAnimal("cow"));
		Integer currHealth = testFarm.getAnimals().getAnimalList().get(0).getHealth();
		Integer currHappiness = testFarm.getAnimals().getAnimalList().get(0).getHappiness();
		testFarm.decreaseAnimalWellbeing();
		assertEquals(currHealth - 5, testFarm.getAnimals().getAnimalList().get(0).getHealth());
		assertEquals(currHappiness - 5, testFarm.getAnimals().getAnimalList().get(0).getHappiness());
	}


	/**
	 * Test for getter and setter of starting balance
	 */
	@Test
	void testStartingBalance() {
		Farm testFarm = new Farm();
		testFarm.setStartingBalance(500.0);
		assertEquals(500.0, testFarm.getStartingBalance());
	}
	
	/**
	 * Test for getter and setter of the farms inventory
	 */
	@Test
	void testGetInventory() {
		InventoryList testInventoryList = new InventoryList();
		Farm testFarm = new Farm();
		testFarm.setInventory(testInventoryList);
		assertEquals(testInventoryList, testFarm.getItems());
	}
	
	/**
	 * Test the createItem method of the farm
	 */
	@Test
	void testCreateItem() {
		Farm testFarm = new Farm();
		Item newItem = testFarm.createItem("magic");
		assertEquals(newItem.getName(), "Magic Dust");
		Item newItem2 = testFarm.createItem("Water");
		assertEquals(newItem2.getName(), "Water");
		Item newItem3 = testFarm.createItem("feed");
		assertEquals(newItem3.getName(), "Animal Feed");
		Item newItem4 = testFarm.createItem("cake");
		assertEquals(newItem4.getName(), "Frosted Cake");
		Item newItem6 = testFarm.createItem("mineral");
		assertEquals(newItem6.getName(), "Mineral Water");
	}
	
	
	/**
	 * Test the createCrop method of the farm
	 */
	@Test
	void testCreateCrop() {
		Farm testFarm = new Farm();
		testFarm.setType("Plain");
		Crop testCrop1 = testFarm.createCrop("carrot");
		Crop testCrop2 = testFarm.createCrop("tomato");
		Crop testCrop3 = testFarm.createCrop("alpine");
		Crop testCrop4 = testFarm.createCrop("jack");
		assertEquals("Carrot", testCrop1.getName());
		assertEquals("Tomato", testCrop2.getName());
		assertEquals("Alpine Strawberry", testCrop3.getName());
		assertEquals("Jack Fruit", testCrop4.getName());
	}
	
	/**
	 * Test the createAnimalMethod of the farm
	 */
	@Test
	void testCreateAnimal() {
		Farm testFarm = new Farm();
		testFarm.setType("Plain");
		Animal testAnimal1 = testFarm.createAnimal("cow");
		Animal testAnimal2 = testFarm.createAnimal("chicken");
		Animal testAnimal3 = testFarm.createAnimal("pig");
		assertEquals("Cow", testAnimal1.getName());
		assertEquals("Chicken", testAnimal2.getName());
		assertEquals("Pig", testAnimal3.getName());
	}
	
	
	
}
