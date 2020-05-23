package test;

import classes.Animal;
import classes.AnimalList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalListTest {

	
	/**
	 * Test for Setter and Getter of animal list
	 */
	@Test
	void testAnimalList() {
		AnimalList testAnimalList = new AnimalList();
		ArrayList<Animal> testList = new ArrayList<Animal>();
		Animal testAnimal = new Animal();
		testList.add(testAnimal);
		testAnimalList.setAnimalList(testList);
		assertEquals(testList, testAnimalList.getAnimalList());
	}


	/**
	 * Test for adding an animal to the animal list
	 */
	@Test
	void testAddAnimal() {
		AnimalList testAnimalList = new AnimalList();
		Animal testAnimal = new Animal();
		Animal testAnimal2 = new Animal();
		testAnimalList.addAnimal(testAnimal);
		assertEquals(testAnimal, testAnimalList.getAnimalList().get(0));
		testAnimalList.addAnimal(testAnimal2);
		assertEquals(testAnimal2, testAnimalList.getAnimalList().get(1));
		
	}

	
	/**
	 * Test for getting the animal count 
	 * (length of the animal list)
	 */
	@Test
	void testGetAnimalCount() {
		AnimalList testAnimalList = new AnimalList();
		Animal testAnimal = new Animal();
		Animal testAnimal2 = new Animal();
		assertEquals(0, testAnimalList.getAnimalCount());
		testAnimalList.addAnimal(testAnimal);
		assertEquals(1, testAnimalList.getAnimalCount());
		testAnimalList.addAnimal(testAnimal2);
		assertEquals(2, testAnimalList.getAnimalCount());
	}

	
	/**
	 * Test for getEachAnimalCount of Animal List
	 */
	@Test
	void testGetEachAnimalCount() {
		AnimalList testAnimalList = new AnimalList();
		Animal testAnimal = new Animal();
		testAnimal.setName("Cow");
		Animal testAnimal2 = new Animal();
		testAnimal2.setName("Pig");
		testAnimalList.addAnimal(testAnimal);
		assertEquals(1, testAnimalList.getEachAnimalCount().get(0));
		testAnimalList.addAnimal(testAnimal);
		assertEquals(2, testAnimalList.getEachAnimalCount().get(0));
		testAnimalList.addAnimal(testAnimal2);
		assertEquals(1, testAnimalList.getEachAnimalCount().get(1));
	}

	/**
	 * Test for getUniqueAnimalStrings of AnimalList
	 */
	@Test
	void testGetUniqueAnimalStrings() {
		AnimalList testAnimalList = new AnimalList();
		ArrayList<String> testArray = new ArrayList();
		assertEquals(testArray, testAnimalList.getUniqueAnimalStrings());
		Animal testAnimal = new Animal();
		testAnimal.setName("Cow");
		testAnimalList.addAnimal(testAnimal);
		testArray.add("Cow");
		assertEquals(testArray, testAnimalList.getUniqueAnimalStrings());
		Animal testAnimal2 = new Animal();
		testAnimal2.setName("Pig");
		testAnimalList.addAnimal(testAnimal2);
		testArray.add("Pig");
		assertEquals(testArray, testAnimalList.getUniqueAnimalStrings());
		testAnimalList.addAnimal(testAnimal2);
		assertEquals(testArray, testAnimalList.getUniqueAnimalStrings());
	}
	
	
	
}
