package org.unibl.etf.dto;

import org.unibl.etf.model.VehiclePart;

public class PartUsage {
	private VehiclePart vehiclePart;
    private Integer quantity;
	
    public PartUsage() {
		super();
	}

	public PartUsage(VehiclePart vehiclePart, Integer quantity) {
		super();
		this.vehiclePart = vehiclePart;
		this.quantity = quantity;
	}

	public VehiclePart getVehiclePart() {
		return vehiclePart;
	}

	public void setVehiclePart(VehiclePart vehiclePart) {
		this.vehiclePart = vehiclePart;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return vehiclePart.toString() + " x " + quantity;
	}
    
}
