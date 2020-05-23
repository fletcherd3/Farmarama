package test;

import classes.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FarmerTest {
	
	/**
	 * Tests the Getter and Setter of a valid farmer name
	 */
	@Test
	@DisplayName("Test a valid farmer name")
	void setAndGetValidName() {
		Farmer testFarmer = new Farmer();
		assertEquals(true,  testFarmer.setName("Bob"));
		assertEquals("Bob", testFarmer.getName());
		// Test bound for length of name
		assertEquals(true, testFarmer.setName("f".repeat(15)));
		assertEquals("f".repeat(15), testFarmer.getName());
		assertEquals(false, testFarmer.setName("/[!"));
		assertEquals("f".repeat(15), testFarmer.getName());
	}


	/**
	 * Tests the Getter and Setter of an invalid long farmer name
	 */
	@Test
	@DisplayName("Test an invalid long name")
	void setAndGetLongName() {
		Farmer testFarmer = new Farmer();
		// Test 16 character name
		assertEquals(false, testFarmer.setName("qqqqqqqqqqqqqqqq"));
		assertNull(testFarmer.getName());
		// Test 100 character name
		assertEquals(false, testFarmer.setName("f".repeat(100)));
		assertNull(testFarmer.getName());
	}

	/**
	 * Tests the Getter and Setter of an invalid short farmer name
	 */
	@Test
	@DisplayName("Test an invalid short name")
	void setAndGetShortName() {
		Farmer testFarmer = new Farmer();
		// Test 2 character name
		assertEquals(false, testFarmer.setName("12"));
		assertNull(testFarmer.getName());
		// Test 0 character name
		assertEquals(false, testFarmer.setName(""));
		assertNull(testFarmer.getName());
	}


	/**
	 * Tests the Getter and Setter of a valid farmer age
	 */
	@Test
	@DisplayName("Test valid ages")
	void setAndGetValidAge() {
		Farmer testFarmer = new Farmer();
		assertEquals(true, testFarmer.setAge(18));
		assertEquals(18, testFarmer.getAge());
		assertEquals(true, testFarmer.setAge(100));
		assertEquals(100, testFarmer.getAge());
	}


	/**
	 * Tests the Getter and Setter of an invalid farmer age
	 */
	@Test
	@DisplayName("Test invalid ages")
	void setAndGetInvalidAge() {
		Farmer testFarmer = new Farmer();
		assertEquals(false, testFarmer.setAge(-1));
		assertEquals(0, testFarmer.getAge());
		assertEquals(false, testFarmer.setAge(1000));
		assertEquals(0, testFarmer.getAge());
	}
}