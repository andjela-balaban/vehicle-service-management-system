package org.unibl.etf.central.server.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.unibl.etf.dto.*;
import org.unibl.etf.central.server.dto.*;
import org.unibl.etf.central.server.service.*;
import org.unibl.etf.model.*;

@Path("/clients")
public class ClientController {

	private ClientService service;
	
	public ClientController() {
		this.service = new ClientService();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllClients() {
		List<Client> clients = service.getAllClients();
		return Response.status(200).entity(clients).build();
	}
	
	@GET
	@Path("/by-username/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientByUsername(@PathParam("username") String username) {
		Client client = service.findByUsername(username);
		if(client != null) {
			return Response.status(200).entity(client).build();
		}
		else {
			return Response.status(404).entity(new ErrorMessage("Client not found")).build();
		}
	}
	
	@GET
	@Path ("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientById(@PathParam("id") int id) {
		Client client = service.findById(id);
		if(client != null) {
			return Response.status(200).entity(client).build();
		}
		else {
			return Response.status(404).entity(new ErrorMessage("Client not found")).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createClient(RegistrationDTO registrationDto) {
		if(service.createClient(registrationDto)) {
			return Response.status(201).build();
		}
		else {
			return Response.status(409).entity(new ErrorMessage("Username already exists")).build();
		}
	}
	
	@DELETE
	@Path ("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeClient(@PathParam("id") int id) {
		Client client = service.removeClient(id);
		if(client != null) {
			return Response.status(200).entity(client).build();
		}
		else {
			return Response.status(404).build();
		}
	}
	
	
	@POST
	@Path ("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateLogin(LoginDTO loginDto) {
		Client authenticatedClient = service.authenticateLogin(loginDto);
		if(authenticatedClient != null) {
			return Response.status(200).entity(authenticatedClient).build();
		}
		else {
			return Response.status(401).entity(new ErrorMessage("Invalid username or password")).build();
		}
	}
	
	@PUT
	@Path("/{id}/approve")
	@Produces(MediaType.APPLICATION_JSON)
	public Response approveClientAccount(@PathParam("id") int id) {
		boolean result = service.approveClientAccount(id);
		if(result) {
			Client updated = service.findById(id);
			return Response.status(200).entity(updated).build();
		}
		else {
			return Response.status(404).entity(new ErrorMessage("Client not found")).build();
		}
	}
	
	
	@PUT
	@Path("/{id}/block")
	@Produces(MediaType.APPLICATION_JSON)
	public Response blockClientAccount(@PathParam("id") int id) {
		boolean result = service.blockClientAccount(id);
		if(result) {
			Client updated = service.findById(id);
			return Response.status(200).entity(updated).build();
		}
		else {
			return Response.status(404).entity(new ErrorMessage("Client not found")).build();
		}
	}
	
}
