

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * @author as2352, dd387, ru43
 * @version 0.5
 * Receives user name and password from login window
 * returns int to show auth level/auth success
 */
public class Authenticate {

	/**
	 * Checks the inputed user name & password against a file database of users
	 * @param user = the user name to be checked
	 * @param pass = the password to be checked
	 * @return role = an int representing the user's authorisation level or an authentication failure
	 * user = 0, reviewer = 3,  h.r. = 1, director = 2
	 * failed authentication = -1
	 * logged out = 0
	 */

	private static final int USER = 0;
	private static final int HR_EMPLOYEE = 1;
	private static final int DIRECTOR = 2;
	private static final int REVIEWER = 3;

    public int authenticate(String user, String pass, int desiredRole) {
        Boolean hasPw = false;
        File file = new File("users.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            Logging.logger.severe("Error while reading databse file");
            JOptionPane.showMessageDialog(null, "Database error");
        }

        int role = 0;
    	while (sc.hasNextLine()) {
            String S = sc.nextLine();
            if (S.equals(user)) {
            	S = sc.nextLine();
            	if (S.equals(pass)) {
            		S = sc.nextLine();
	                hasPw = true;
	                if (desiredRole == 0) {
	                	role = USER; // every role can login with the default user permission
	                   Logging.logger.info(user + " logged in with USER role. Authorization successful.");
	                } else if (S.contains("HR Employee") && desiredRole == HR_EMPLOYEE) {
	                    role = HR_EMPLOYEE;
	                    Logging.logger.info(user + " logged in with HR EMPLOYEE role. Authorization successful.");
	                } else if (S.contains("Director") && desiredRole == DIRECTOR) {
	                    role = DIRECTOR;
	                    Logging.logger.info(user + " logged in with DIRECTOR role. Authorization successful.");
	                } else if (S.contains("Reviewer") && desiredRole == REVIEWER) {
	                    role = REVIEWER;
	                    Logging.logger.info(user + " logged in with REVIEWER role. Authorization successful.");
	                } else {
	                    role = -1; // failure for wrong authorisation
	                    Logging.logger.severe(user + " has tried logging in with wrong authorization level.");
	                    JOptionPane.showMessageDialog(null, "You have no authorized access to login as this role");

	                }
            	}
    		}
        }
        if (hasPw == false) {
            Logging.logger.severe(user + " has tried to log in with wrong password or username.");
            JOptionPane.showMessageDialog(null, "Incorrectly entered username or password.");
            //-1 failure (in case they want to add more roles they can always go +1 so 5 is not taken.)
            role = -1;

        }
        return role;
    }
}


