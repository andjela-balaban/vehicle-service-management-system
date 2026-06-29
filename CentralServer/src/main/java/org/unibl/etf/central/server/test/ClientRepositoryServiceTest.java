package org.unibl.etf.central.server.test;

import static org.unibl.etf.logger.MyLogger.logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;

import org.unibl.etf.dto.*;
import org.unibl.etf.central.server.service.*;
import org.unibl.etf.model.*;
import org.unibl.etf.properties.*;

public class ClientRepositoryServiceTest {
	private static Path xmlFile;
	
	public static void main(String[] args) {
		System.out.println(new File("/Users/andjelabalaban/eclipse-workspace4/resources/config.properties").exists());
		xmlFile = Paths.get(ConfigManager.CLIENTS_XML_PATH);
		ClientService service = new ClientService();
		RegistrationDTO c = new RegistrationDTO("Marko", "Markovic", new Vehicle(), "Adresa 123", "065/123-321", "markom@example.com", "mMarko", "pass123");
        service.createClient(c);

        List<String> lines;
		try {
			lines = Files.readAllLines(xmlFile);
			 boolean contains = lines.stream().anyMatch(line -> line.contains("mMarko"));
			 System.out.println("XML fajl sadrzi klijenta - " +  (contains ? "DA" : "NE"));
		} catch (IOException e) {
			e.printStackTrace();
			logger.log(Level.WARNING, "Exception: ", e);
		}
	}
}
