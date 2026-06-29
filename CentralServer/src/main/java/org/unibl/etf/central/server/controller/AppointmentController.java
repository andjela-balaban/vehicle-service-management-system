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

@Path("/appointments")
public class AppointmentController {

private AppointmentService service;
	
	public AppointmentController() {
		this.service = new AppointmentService();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAppointments() {
		List<Appointment> appointments = service.getAllAppointments();
		return Response.status(200).entity(appointments).build();
	}
	
	@GET
	@Path ("/client/{clientId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientAppointments(@PathParam("clientId") int clientId) {
		List<Appointment> appointments = service.getAllClientAppointments(clientId);
		return Response.status(200).entity(appointments).build();
	}
	
	@GET
	@Path("/client/{clientId}/history")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientHistoryAppointments(@PathParam("clientId") int clientId) {
		List<Appointment> appointments = service.getClientHistoryAppointments(clientId);
		return Response.status(200).entity(appointments).build();
	}
	
	
	@GET
	@Path ("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAppontmentById(@PathParam("id") int id) {
		Appointment appointment = service.findById(id);
		if(appointment != null) {
			return Response.status(200).entity(appointment).build();
		}
		else {
			return Response.status(404).entity(new ErrorMessage("Appointment not found")).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewAppointment(Appointment appointment) {
		if(service.createNewAppointment(appointment)) {
			return Response.status(201).build();
		}
		else {
			return Response.status(409).entity(new ErrorMessage("Appointment already exists")).build();
		}
	}
	
	@POST
	@Path ("/booking")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response bookAppointment(AppointmentDTO appointmentDto) {
		boolean booked = service.bookAppointment(appointmentDto);
		if(booked) {
			return Response.status(201).build();
		}
		else {
			return Response.status(409).entity(new ErrorMessage("Appointment is not available")).build();
		}
	}
	
	@DELETE
	@Path ("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeAppointment(@PathParam("id") int id) {
		Appointment appointment = service.removeAppointment(id);
		if(appointment != null) {
			return Response.status(200).entity(appointment).build();
		}
		else {
			return Response.status(404).entity(new ErrorMessage("Appointment not found")).build();

		}
	}
	
	@PUT
	@Path("/{id}/complete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response completeAppointment(@PathParam("id") int id) {
		boolean result = service.completeAppointment(id);
		if(result) {
			Appointment updated = service.findById(id);
			return Response.status(200).entity(updated).build();
		}
		else {
			return Response.status(404).entity(new ErrorMessage("Appointment not found")).build();
		}
	}
	
	
	@PUT
	@Path("/{id}/cancel")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelAppointment(@PathParam("id") int id) {
		boolean result = service.cancelAppointment(id);
		if(result) {
			Appointment updated = service.findById(id);
			return Response.status(200).entity(updated).build();
		}
		else {
			return Response.status(404).entity(new ErrorMessage("Appointment not found")).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAppointment(@PathParam("id") int id, Appointment updatedAppointment) {
	    boolean result = service.updateAppointment(id, updatedAppointment);
	    if (result) {
	        Appointment updated = service.findById(id);
	        return Response.status(200).entity(updated).build();
	    } else {
	        return Response.status(404).entity(new ErrorMessage("Appointment not found")).build();
	    }
	}

}
