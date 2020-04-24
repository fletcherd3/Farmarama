package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import classes.Farm;

class FarmTest {
	
	@Test
	void nameTest() {
		Farm testFarm = new Farm();
		testFarm.setName("Goggo");
		assertEquals("Goggo", testFarm.getName());
		testFarm.setName("Name2");
		assertEquals("Name2", testFarm.getName());
	}
	
	@Test
	void typeTest() {
		Farm testFarm = new Farm();
		testFarm.setType("Plain");
		assertEquals("Plain", testFarm.getType());
		
	}

}
