package org.unibl.etf.model;

import java.util.Objects;

public class Supplier {
	private static int SUPPLIER_COUNT = 1;
	
	private int id;        
    private String name;
    private String phoneNumber;    
    private String email;
    
    public Supplier() {
    	super();
    	this.id = SUPPLIER_COUNT++;
    }
    
	public Supplier(String name, String phoneNumber, String email) {
		this();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Supplier other = (Supplier) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}
}
