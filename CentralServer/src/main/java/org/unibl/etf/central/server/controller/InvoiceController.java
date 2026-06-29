package org.unibl.etf.central.server.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.unibl.etf.central.server.dto.ErrorMessage;
import org.unibl.etf.central.server.service.*;
import org.unibl.etf.model.Invoice;

@Path("/invoices")
public class InvoiceController {
	
	private InvoiceService service;
	
	public InvoiceController() {
		service = new InvoiceService();
	}
	
	@POST
	@Path("/send/{clientId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendInvoice(@PathParam("clientId") int clientId, Invoice invoice) {
		if(service.sendInvoice(invoice)) {
			return Response.status(200).entity(invoice).build();
		}
		else {
			return Response.status(500).entity(new ErrorMessage("Unable to send invoice")).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createInvoice(Invoice invoice) {
		if(service.createInvoice(invoice)) {
			return Response.status(200).entity(invoice).build();
		}
		else {
			return Response.status(500).entity(new ErrorMessage("Unable to create invoice")).build();
		}
	}

}
