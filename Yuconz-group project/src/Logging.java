

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/** 
 * @author as2352
 * @version 0.5
 * Used to log whenever an action regarding a file is made by the user
 * logger can be called with Logging.logger.info("message");
 * log formatting is handled here
 */
public class Logging {

    static Logger logger = Logger.getLogger("GLOBAL");
	static FileHandler fh = null;

    
	/*
	 * Creates log file with the date-time format M-d_HHmmss
	 */
	public static void format() {
    	SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
    	try {
           fh = new FileHandler("YuconzLogFile_" + format.format(Calendar.getInstance().getTime()) + ".log");
    	} catch (Exception e) { //
           e.printStackTrace();
    	}
    	fh.setFormatter(new SimpleFormatter());
    	logger.addHandler(fh);
   }
}