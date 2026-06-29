package org.unibl.etf.central.server.repository;


import static org.unibl.etf.logger.MyLogger.logger;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

import org.unibl.etf.model.Client;
import org.unibl.etf.model.Clients;
import org.unibl.etf.properties.ConfigManager;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;


public class ClientRepository {

    private static final String FILE_PATH = ConfigManager.CLIENTS_XML_PATH;
    private Clients clients;
    private static ClientRepository instance = null;

    private ClientRepository() {
        load();
    }

    private void load() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(Clients.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                clients = (Clients) unmarshaller.unmarshal(file);
            } else {
                clients = new Clients();
                save(); 
            }
        } catch (JAXBException e) {
            clients = new Clients();
            e.printStackTrace();
            logger.log(Level.WARNING, "Exception: ", e);
        }
    }

    public void save() {
        try {
            JAXBContext context = JAXBContext.newInstance(Clients.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(clients, new File(FILE_PATH));
        } catch (JAXBException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Exception: ", e);
        }
    }

    public List<Client> getClients() { 
    	return clients.getClients();
    }
    
    public static ClientRepository getInstance() {
    	if(instance == null) {
    		instance = new ClientRepository();
    	}
    	return instance;
    }
}

