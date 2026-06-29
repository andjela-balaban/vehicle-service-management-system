package org.unibl.etf.model;

import java.util.Objects;

public class Vehicle {

	private String id;
	private String model;
	private String manufacturer;
	private int yearOfProduction;
	
	public Vehicle() {
		super();
	}

	public Vehicle(String id, String model, String manufacturer, int yearOfProduction) {
		super();
		this.id = id;
		this.model = model;
		this.manufacturer = manufacturer;
		this.yearOfProduction = yearOfProduction;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
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
		Vehicle other = (Vehicle) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", model=" + model + ", manufacturer=" + manufacturer + ", yearOfProduction="
				+ yearOfProduction + "]";
	}
}
