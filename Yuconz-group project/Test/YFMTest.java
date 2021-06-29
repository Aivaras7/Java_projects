import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//Warning this tests file creation, if it fails
//check any files it creates, don't already exist or have have any read/write problems
/**
 * Unit tests for the YuconzFileManager class
 * @author dd387
 *
 */
class YFMTest {

	private YuconzFileManager pDFM;
	private String fileName = "StaffID";
	private File file;
	private String[] inputs = new String[]{"John", "1", "13/02/1999", "Adresss", "town", "county", "postcode", "tnumber", "mnumber", "mergency contact", "E number"};

	@BeforeEach
	public void setup() {
		file = new File("pd/" + fileName + ".txt");
		file.deleteOnExit();
		pDFM = new YuconzFileManager("StaffID");

	}

	@Test
	public void pdTest() {

		assertTrue(pDFM.createPD(fileName));
		assertTrue(file.exists());

		assertFalse(pDFM.createPD(fileName));

		assertTrue(pDFM.amendRecord(inputs, file));
		assertArrayEquals("Strings not equal",pDFM.readRecord(file), inputs);
	}


	@AfterEach
	public void cleanup() {

	}

}
