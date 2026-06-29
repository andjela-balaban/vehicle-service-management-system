//package org.unibl.etf.test;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.unibl.etf.logger.MyLogger;
//import org.unibl.etf.properties.ConfigManager;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.logging.Level;
//
//public class TestLogger {
//	
//	private static Path logFile;
//
//    @BeforeAll
//    static void setup() {
//    	// provjeri da je path iz configa ucitan
//        assertNotNull(ConfigManager.LOGGER_PATH, "LOGGER_PATH ne smije biti null!");
//        logFile = Paths.get(ConfigManager.LOGGER_PATH);
//    }
//
//    @Test
//    void testLoggerWritesMessage() throws IOException, InterruptedException {
//        String testMessage = "JUnit test poruka";
//
//        // logovanje test poruke
//        MyLogger.logger.log(Level.INFO, testMessage);
//
//        // cekamo upis u fajl
//        Thread.sleep(200);
//
//        assertTrue(Files.exists(logFile), "Log fajl ne postoji: " + logFile);
//
//        // ucitavanje svih linija fajla
//        List<String> lines = Files.readAllLines(logFile);
//
//        // provjera da li se poruka nalazi u log fajlu
//        boolean contains = lines.stream().anyMatch(line -> line.contains(testMessage));
//        assertTrue(contains, "Log fajl ne sadrzi testnu poruku!");
//    }
//}
