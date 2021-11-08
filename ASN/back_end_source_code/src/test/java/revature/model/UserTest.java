package revature.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	
	static User user;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		user = new User(); 
	}
	

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		System.out.println("User After All ID : " + user.getAsnUserId());
	}

	@BeforeEach
	void setUp() throws Exception {
		
		user = new User(); 
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
		System.out.println("User After Each ID : " + user.getAsnUserId());
	}

	@Test
	void test() {
		System.out.println("Learning about tests....");
	}
	
	@Test
	void testingUserGettersAndSetters() {
		
		System.out.println("Creating a new user");
		
		user.setAsnUserId(5);
		assertEquals(user.getAsnUserId(), 5);
		user.setAsnUserId(8);
		assertEquals(user.getAsnUserId(), 8);
		user.setAsnUserId(4000);
		assertEquals(user.getAsnUserId(), 4000);
		user.setAsnUserId(-5);
		assertEquals(user.getAsnUserId(), -5);
		
	}

}
