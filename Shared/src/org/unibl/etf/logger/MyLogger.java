package org.unibl.etf.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unibl.etf.properties.Util;
import static org.unibl.etf.properties.ConfigManager.*;


public class MyLogger {
	
	private MyLogger() { 
		super(); 
	}
	
//	@SuppressWarnings("exports")
	public static final Logger logger;
	
	static {
		logger = Logger.getGlobal();
		try {
			FileHandler fh = new FileHandler(Util.buildPath(LOGGER_PATH));
			logger.addHandler(fh);
		} catch (SecurityException | IOException e) {
			logger.log(Level.WARNING, "Exception: ", e);
		}
	}
}