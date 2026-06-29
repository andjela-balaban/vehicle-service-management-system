package org.unibl.etf.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class VehiclePart {
	private String code;
	private String name;
	private String manufacturer;
	private double price;
	private int availableQuantity;
	private String description;
	
	public VehiclePart() {
		super();
	}

	public VehiclePart(String code, String name, String manufacturer, double price, 
			int availableQuantity, String description) {
		this();
		this.code = code;
		this.name = name;
		this.manufacturer = manufacturer;
		this.price = price;
		this.availableQuantity = availableQuantity;
		this.description = description;
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
	
	public Map<String, String> toMap(){
		Map<String, String> hash = new HashMap<>();
        hash.put("name", this.getName());
        hash.put("manufacturer", this.getManufacturer());
        hash.put("price", String.valueOf(this.getPrice()));
        hash.put("availableQuantity", String.valueOf(this.getAvailableQuantity()));
        hash.put("description", this.getDescription());
        return hash;
	}

	public static VehiclePart fromMap(String code, Map<String, String> hash) {
	    if (hash == null || hash.isEmpty())
	        return null;

	    VehiclePart part = new VehiclePart();
	    part.setCode(code);
	    part.setName(hash.get("name"));
	    part.setManufacturer(hash.get("manufacturer"));
	    part.setPrice(Double.parseDouble(hash.get("price")));
	    part.setAvailableQuantity(Integer.parseInt(hash.get("availableQuantity")));
	    part.setDescription(hash.get("description"));
	    return part;
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
		VehiclePart other = (VehiclePart) obj;
		return Objects.equals(code, other.code);
	}

	@Override
	public String toString() {
		return code + ":  " + name + " " + price + "€";
	}	
}
