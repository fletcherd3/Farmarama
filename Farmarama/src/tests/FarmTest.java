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
		
	}

}
