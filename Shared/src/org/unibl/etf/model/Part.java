package org.unibl.etf.model;

import java.util.Objects;

public class Part {
	private static int PART_COUNT = 1;
	
	private int id;
	private String code;
	private String name;
	private String manufacturer;
	private double price;
	private int availableQuantity;
	private String description;
	
	public Part() {
		super();
		this.id = PART_COUNT++;
	}

	public Part(String code, String name, String manufacturer, double price, int availableQuantity,
			String description) {
		this();
		this.code = code;
		this.name = name;
		this.manufacturer = manufacturer;
		this.price = price;
		this.availableQuantity = availableQuantity;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Part other = (Part) obj;
		return Objects.equals(code, other.code);
	}

	@Override
	public String toString() {
		return "Part [code=" + code + ", name=" + name + ", price=" + price + "]";
	}	
}
