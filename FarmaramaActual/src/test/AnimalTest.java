package test;
import classes.Animal;
import classes.Item;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


class AnimalTest {

	
	
	
	/**
	 * Tests the getter and setter of Animals Health
	 */
	@Test
	void testHealth() {
		Animal testAnimal = new Animal();
		testAnimal.setHealth(100);
		assertEquals(testAnimal.getHealth(), 100);
		testAnimal.setHealth(120);
		assertEquals(testAnimal.getHealth(), 120);
	}

	
	/**
	 * Tests the getter and setter of Animals happiness
	 */
	@Test
	void testHappiness() {
		Animal testAnimal = new Animal();
		testAnimal.setHappiness(100);
		assertEquals(testAnimal.getHappiness(), 100);
		testAnimal.setHappiness(120);
		assertEquals(testAnimal.getHappiness(), 100);
		testAnimal.setHappiness(40);
		assertEquals(testAnimal.getHappiness(), 40);
	}
	
	/**
	 * Tests the getter and setter of Animals name
	 */
	@Test
	void testName() {
		Animal testAnimal = new Animal();
		testAnimal.setName("Jared");
		assertEquals("Jared", testAnimal.getName());
		testAnimal.setName("test2");
		assertEquals("test2", testAnimal.getName());
	}
	
	
	/**
	 * Tests the feed method of animal
	 */
	@Test
	void testFeed() {
		Animal testAnimal = new Animal();
		Item testItem = new Item();
		testItem.setHealthValue(30);
		testAnimal.setHealth(60);
		testAnimal.feed(testItem);
		assertEquals(90, testAnimal.getHealth());
		testAnimal.setHealth(95);
		testItem.setHealthValue(15);
		testAnimal.feed(testItem);
		assertEquals(100, testAnimal.getHealth());
	}

	
	/**
	 * Test the Getter and Setter of purchasePrice
	 */
	@Test
	void testPurchasePrice() {
		Animal testAnimal = new Animal();
		testAnimal.setPurchasePrice(30.0);
		assertEquals(30.0, testAnimal.getPurchasePrice());
		testAnimal.setPurchasePrice(556.0);
		assertEquals(556.0, testAnimal.getPurchasePrice());
	}
	
	
	/**
	 * Test the Getter and Setter of tendReturn
	 */
	@Test
	void testGetTendReturn() {
		Animal testAnimal = new Animal();
		testAnimal.setTendReturn(10.0);
		assertEquals(10.0, testAnimal.getTendReturn());
		testAnimal.setTendReturn(3.0);
		assertEquals(3.0, testAnimal.getTendReturn());
	}
	
	
	/**
	 * Test the play method of Animal and that happiness
	 * is correctly updated
	 */
	@Test
	void testPlay() {
		Animal testAnimal = new Animal();
		testAnimal.setHappiness(30);
		testAnimal.play();
		assertEquals(40, testAnimal.getHappiness());
		testAnimal.setHappiness(95);
		testAnimal.play();
		assertEquals(100, testAnimal.getHappiness());
		
	}


	


}
