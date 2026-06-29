package org.unibl.etf.properties;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import static org.unibl.etf.logger.MyLogger.logger;
//
//public class ConfigManager {
//
//	private ConfigManager() {
//		super();
//	}
//	
//	static {
//		try {
//			FileReader reader = new FileReader(Util.buildPath("../resources/config.properties")); 
//			Properties p = new Properties(); 
//			p.load(reader);
//			
//			LOGGER_PATH = Util.buildPath(p.getProperty("LOGGER_PATH"));
//			CLIENTS_XML_PATH = Util.buildPath(p.getProperty("CLINETS_XML_PATH"));
//			APPOINTMENTS_XML_PATH = Util.buildPath(p.getProperty("APPOINTMENTS_XML_PATH"));
//			
//		} catch(IOException e) {
//			e.printStackTrace();
//			logger.log(Level.WARNING, "Exception: ", e);
//		}
//		
//	}
//	
//	public static String LOGGER_PATH;
//	
//	public static String CLIENTS_XML_PATH;
//	
//	public static String APPOINTMENTS_XML_PATH;
//
//}




//LOGGER_PATH=../resources/logs/logger.log
//CLIENTS_XML_PATH=../resources/clinets/clinets.xml
//APPOINTMENTS_XML_PATH=../resources/appointments/appointments.xml

public class ConfigManager {

	private ConfigManager() {
		super();
	}
	
	static {
		try {
//			FileReader reader = new FileReader(Util.buildPath("../resources/config.properties"));
			FileReader reader = new FileReader(new File("/Users/andjelabalaban/eclipse-workspace4/resources/config.properties"));
			System.out.println(new File("/Users/andjelabalaban/eclipse-workspace4/resources/config.properties").exists());
			Properties p = new Properties(); 
			p.load(reader);
			
			LOGGER_PATH = Util.buildPath(p.getProperty("LOGGER_PATH"));
			CLIENTS_XML_PATH = Util.buildPath(p.getProperty("CLIENTS_XML_PATH"));
			APPOINTMENTS_XML_PATH = Util.buildPath(p.getProperty("APPOINTMENTS_XML_PATH"));
			
		} catch(IOException e) {
			e.printStackTrace();
			logger.log(Level.WARNING, "Exception: ", e);
		}
		
	}
	
//	static {
//	    try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
//	        if (input == null) {
//	            throw new RuntimeException("config.properties nije pronađen u classpath-u!");
//	        }
//
//	        Properties p = new Properties();
//	        p.load(input);
//
//	        LOGGER_PATH = p.getProperty("LOGGER_PATH");
//	        CLIENTS_XML_PATH = p.getProperty("CLIENTS_XML_PATH");
//	        APPOINTMENTS_XML_PATH = p.getProperty("APPOINTMENTS_XML_PATH");
//
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	        // ako koristiš logger, možeš ovdje logovati
//	    }
//	}

	
	public static String LOGGER_PATH;
	
	public static String CLIENTS_XML_PATH;
	
	public static String APPOINTMENTS_XML_PATH;

}