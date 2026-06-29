package org.unibl.etf.central.server.test;

import static org.unibl.etf.logger.MyLogger.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;

import org.unibl.etf.dto.*;
import org.unibl.etf.central.server.service.*;
import org.unibl.etf.model.*;
import org.unibl.etf.properties.ConfigManager;

public class AppointmentRepositoryServiceTest {
	
	private static Path xmlFile;
	
	
	public static void main(String[] args) {
		xmlFile = Paths.get(ConfigManager.APPOINTMENTS_XML_PATH);
		AppointmentService service = new AppointmentService();
		AppointmentDTO adto = new AppointmentDTO(LocalDate.now(), LocalTime.now(), TypeOfService.REPAIR, 1);
        service.bookAppointment(adto);
        List<String> lines;

		try {
			lines = Files.readAllLines(xmlFile);
			 boolean contains = lines.stream().anyMatch(line -> line.contains("REPAIR"));
			 System.out.println("XML fajl sadrzi termin - " +  (contains ? "DA" : "NE"));
		} catch (IOException e) {
			e.printStackTrace();
			logger.log(Level.WARNING, "Exception: ", e);
		}
	}
}
