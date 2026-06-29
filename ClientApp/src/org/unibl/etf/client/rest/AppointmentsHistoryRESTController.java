package org.unibl.etf.client.rest;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.unibl.etf.model.Appointment;
import org.unibl.etf.properties.ConfigManager;

public class AppointmentsHistoryRESTController { 
	
	public static List<Appointment> getHistoryAppointments(int clientId) {
        Client client = ClientBuilder.newClient();
        String url = ConfigManager.APPOINTMENTS_HISTORY_URL;
        url = url.replace("{clientId}", clientId + "");
        WebTarget target = client.target(url);

        Response response = target.request(MediaType.APPLICATION_JSON).get();
        
        if (response.getStatus() == 200) {
            List<Appointment> appointments = response.readEntity(
                new javax.ws.rs.core.GenericType<List<Appointment>>() {}
            );
            return appointments;
        } else {
            System.out.println("Error: " + response.getStatus());
            return null;
        }
    }
}
