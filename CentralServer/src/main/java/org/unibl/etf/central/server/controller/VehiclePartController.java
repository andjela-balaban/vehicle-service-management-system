package org.unibl.etf.central.server.controller;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.unibl.etf.central.server.dto.*;
import org.unibl.etf.central.server.service.VehiclePartService;
import org.unibl.etf.model.*;

@Path("/parts")
public class VehiclePartController {
	private VehiclePartService service;
	
	public VehiclePartController() {
		this.service = new VehiclePartService();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllParts() {
		List<VehiclePart> parts = service.getAllParts();
		return Response.status(200).entity(parts).build();
	}
	
	@GET
	@Path ("/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPartByCode(@PathParam("code") String code) {
		VehiclePart part = service.findByCode(code);
		if(part != null) {
			return Response.status(200).entity(part).build();
		}
		else {
			return Response.status(404).entity(new ErrorMessage("Part not found")).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPart(VehiclePart part) {
		if(service.createPart(part)) {
			return Response.status(201).build();
		}
		else {
			return Response.status(409).entity(new ErrorMessage("Part's code already exists")).build();
		}
	}
	
	@DELETE
	@Path ("/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removePart(@PathParam("code") String code) {
		VehiclePart part = service.removePart(code);
		if(part != null) {
			return Response.status(200).entity(part).build();
		}
		else {
			return Response.status(404).build();
		}
	}
	
	@PUT
	@Path("/{code}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePart(@PathParam("code") String code, VehiclePart part) {
		if(service.updatePart(code, part) != null) {
			return Response.status(200).entity(part).build();
		}
		else {
			return Response.status(404).entity(new ErrorMessage("Part not updated")).build();
		}
	}
}
