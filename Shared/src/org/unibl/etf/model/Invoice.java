package org.unibl.etf.model;

import java.util.List;
import java.util.Objects;

public class Invoice {
	private static int INVOICE_COUNT = 1;
	
	private int id;
	private Client client;
	private Appointment appointment;
	private List<Part> parts;
	private double amount;
	
	public Invoice() {
		super();
		this.id = INVOICE_COUNT++;
	}

	public Invoice(Client client, Appointment appointment, List<Part> parts, double amount) {
		this();
		this.client = client;
		this.appointment = appointment;
		this.parts = parts;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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
		Invoice other = (Invoice) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", client=" + client.getFirstName() + " " + client.getLastName() + "]";
	}
}
