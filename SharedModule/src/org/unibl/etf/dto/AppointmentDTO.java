package org.unibl.etf.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import org.unibl.etf.model.TypeOfService;

public class AppointmentDTO {
	private LocalDate date;
	private LocalTime time;
	private TypeOfService serviceType;
	private int clientId;
	
	public AppointmentDTO() {
		super();
	}

	public AppointmentDTO(LocalDate date, LocalTime time, TypeOfService serviceType, int clientId) {
		super();
		this.date = date;
		this.time = time;
		this.serviceType = serviceType;
		this.clientId = clientId;	
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public TypeOfService getServiceType() {
		return serviceType;
	}

	public void setServiceType(TypeOfService serviceType) {
		this.serviceType = serviceType;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
}
