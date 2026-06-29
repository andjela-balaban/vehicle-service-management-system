package org.unibl.etf.service.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.unibl.etf.model.Invoice;
import org.unibl.etf.properties.ConfigManager;

public class InvoiceRESTController {

	private static String BASE_URL = ConfigManager.INVOICE_URL;
	
	
	public Invoice createInvoice(Invoice invoice) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		
		Response response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(invoice, MediaType.APPLICATION_JSON));
		if(response.getStatus() == 200) {
			Invoice invoiceRes = response.readEntity(Invoice.class);
			return invoiceRes;
		}
		return null;
	}
	
	
	public Invoice sendInvoice(int clientId, Invoice invoice) {
		Client client = ClientBuilder.newClient();
		String url = BASE_URL + "/send/" + clientId;
		WebTarget target = client.target(url);
		
		Response response = target.request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(invoice, MediaType.APPLICATION_JSON));
		
		if(response.getStatus() == 200) {
			Invoice invoiceRes = response.readEntity(Invoice.class);
			return invoiceRes;
		}
		return null;
	}
}
