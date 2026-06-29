package org.unibl.etf.client.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.unibl.etf.dto.LoginDTO;
import org.unibl.etf.properties.ConfigManager;

public class LoginRESTController {
	
	public static org.unibl.etf.model.Client authenticateLoginRequest(String username, String password) {
		
		Client client = ClientBuilder.newClient();
		String url = ConfigManager.LOGIN_URL;
		WebTarget target = client.target(url);
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(new LoginDTO(username, password), MediaType.APPLICATION_JSON));
		if(response.getStatus() == 200) {
			return response.readEntity(org.unibl.etf.model.Client.class);
		}
		return null;
	}
}
