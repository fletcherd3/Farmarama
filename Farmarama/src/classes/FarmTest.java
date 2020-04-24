package classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FarmTest {

	@Test
	void nameTest() {
		Farm testFarm = new Farm();
		testFarm.setName("Goggo");
		assertEquals("Goggo", testFarm.getName());
		
	}

}
