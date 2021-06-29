import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * Manages file I/O for the OpenPersonalDetails class
 * @author dd387
 * @version
 */
public class YuconzFileManager {

	String user;


	YuconzFileManager(String u){
		user = u;
	}

	/**
	 * Creates a new text file
	 * Called by createReviewRecord()
	 * @param fileName
	 */
	public void createReview(String fileName) {
		File file = new File("reviews/" + fileName + ".txt");

		try {
			if (!file.createNewFile()) {
				Logging.logger.severe("Error while creating PD file");
			}
		} catch (IOException e) {
			Logging.logger.severe("Error while creating PD file");
		}

	}


	/**
	 * Creates a new text file
	 * Returns Boolean for success/fail
	 * Called by createPersonalDetails()
	 * @param fileName
	 * @return Boolean
	 */
	public Boolean createPD(String fileName) {

		File file = new File("pd/" + fileName + ".txt");

		try {
			if (!file.createNewFile()) {
				Logging.logger.severe("Error while creating PD file");
				return false;
			}
		} catch (IOException e) {
			Logging.logger.severe("Error while creating PD file");
			return false;
		}

		return true;
	}

	/**
	 * Reads the File file
	 * Returns the text in the file as a string array
	 * @param file
	 * @return
	 */
	public String[] readRecord(File file) {
		List<String> detailsList = new ArrayList<>();

		Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            Logging.logger.severe("Error while reading database file");
            return null;
        }
        String S = sc.nextLine();
        while (sc.hasNextLine()) {



    		detailsList.add(S);
    		S = sc.nextLine();

    	}

        Object[] objResults = detailsList.toArray();
        String[] results = Arrays.copyOf(objResults, objResults.length, String[].class);
        sc.close();
		return results;
	}

	/**
	 * Takes a string array inputs and writes them to file file.
	 * Returns Boolean for success/fail
	 * @param inputs
	 * @param fileName
	 * @return Boolean
	 */
	public Boolean amendRecord(String[] inputs, File file){
		FileWriter writer = null;
		try {
			 writer = new FileWriter(file);
		} catch (IOException e) {
			Logging.logger.severe("Error while attempting to write to file: " + file.getName() + ", for user: " + user);
		}

		for(String t: inputs){
			try {
				writer.write(t + System.lineSeparator());
				writer.flush();
			} catch (IOException e) {
				Logging.logger.info("Error while attempting to write to file: " + file.getName() + ", for user: " + user);
			}

		}
		try {
			writer.close();
		} catch (IOException e) {

		}
		Logging.logger.info(user + " wrote to file: " + file.getName());

		return true;
	}


	/**
	 * returns a list of user the current user has permission  to review
	 * @return results
	 */
	public String[] checkReviewees() {
		String[] results = new String[100];
		File file = new File("reviewers.txt");

		Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            Logging.logger.severe("Error while reading database file");
            return null;
        }

        int i = 0;
        Boolean done = false;
        String S = sc.next();
        while (sc.hasNextLine() && !done) {
        	if(S.startsWith(user)) {
        		while(sc.hasNext()) {
        			S = sc.next();
        			results[i] = S;
        			i++;
        		}
        		done = true;
        	}

    		S = sc.nextLine();

    	}


        sc.close();
        return results;
	}


	/**
	 * Sets the given file read-only
	 * @param file
	 */
	public void finishReview(File file) {
		file.setReadOnly();
	}



}
