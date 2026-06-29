package org.unibl.etf.central.server.repository;

import static org.unibl.etf.logger.MyLogger.logger;
import java.util.logging.Level;
import java.io.File;
import java.util.List;
import org.unibl.etf.model.Appointment;
import org.unibl.etf.model.Appointments;
import org.unibl.etf.properties.ConfigManager;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class AppointmentRepository {

	private static final String FILE_PATH = ConfigManager.APPOINTMENTS_XML_PATH;
    private Appointments appointments;
    private static AppointmentRepository instance = null;

    private AppointmentRepository() {
        load();
    }

    private void load() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists() && file.length() > 0) {
                JAXBContext context = JAXBContext.newInstance(Appointments.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                appointments = (Appointments) unmarshaller.unmarshal(file);
            } else {
                appointments = new Appointments();
                save();
            }
        } catch (JAXBException e) {
        	appointments = new Appointments();
            e.printStackTrace();
            logger.log(Level.WARNING, "Exception: ", e);
        }
    }


    public void save() {
        try {
            JAXBContext context = JAXBContext.newInstance(Appointments.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(appointments, new File(FILE_PATH));
        } catch (JAXBException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Exception: ", e);
        }
    }

    public List<Appointment> getAppointments() { 
    	return appointments.getAppointments();
    }
    
    public static AppointmentRepository getInstance() {
        if (instance == null) {
            instance = new AppointmentRepository();
        }
        return instance;
    }
}