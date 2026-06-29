package org.unibl.etf.model;

import java.time.*;
import java.util.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "appointment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Appointment {
	private static int APPOINTMENT_COUNT = 1;
	
	@XmlElement
	private int id;
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate date;
	@XmlJavaTypeAdapter(LocalTimeAdapter.class)
	private LocalTime time;
	@XmlElement
	private TypeOfService serviceType;
	@XmlElement
	private int clinetId;
	@XmlElement
	private AppointmentStatus status;
	@XmlElement
	private String comment;
	
	public Appointment() {
		super();
		this.id = APPOINTMENT_COUNT++;
	}

	public Appointment(LocalDate date, LocalTime time, TypeOfService serviceType, int clinetId,
			AppointmentStatus status, String comment) {
		this();
		this.date = date;
		this.time = time;
		this.serviceType = serviceType;
		this.clinetId = clinetId;
		this.status = status;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getClinetId() {
		return clinetId;
	}

	public void setClinetId(int clinetId) {
		this.clinetId = clinetId;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", time=" + time + ", serviceType=" + serviceType + ", clinetId="
				+ clinetId + ", status=" + status + ", comment=" + comment + "]";
	}
}
