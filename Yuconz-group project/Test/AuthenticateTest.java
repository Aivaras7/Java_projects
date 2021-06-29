import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Unit tests for the Authenticate class
 * @author dd387, ru43
 * @version 0.5
 *
 */
public class AuthenticateTest {
	private Authenticate auth;
	String user;
	String pw;
	int desiredRole;
	File file;

	@BeforeEach
	public void setup() throws IOException {
		auth = new Authenticate();
		user = "John";
		pw = "password";
		desiredRole = 0;
		file = new File("Test/" + System.currentTimeMillis()+".txt");
		file.createNewFile();
	}


	/**
	 * Tests the Authenticate class which checks the user name, password and desired role
	 * against a database of valid options
	 */
	@Test
	public void loginTests() {
		//Director selects USER role
		assertEquals(auth.authenticate(user, pw, desiredRole), 0);
		desiredRole = 2;
		//Director selects DIRECTOR role
		assertEquals(auth.authenticate(user, pw, desiredRole), 2);

		user = "Mike";
		pw = "pass";
		desiredRole = 0;
		//Reviewer selects USER role
		assertEquals(auth.authenticate(user, pw, desiredRole), 0);
		desiredRole = 3;
		//Reviewer selects Reviewer role
		assertEquals(auth.authenticate(user, pw, desiredRole), 3);

		user = "Stacy";
		pw = "qwerty";
		desiredRole = 0;
		//HR_Employee selects USER role
		assertEquals(auth.authenticate(user, pw, desiredRole), 0);
		desiredRole = 1;
		//HR_Employee selects HR_EMPLOYEE role
		assertEquals(auth.authenticate(user, pw, desiredRole), 1);
	}
}
