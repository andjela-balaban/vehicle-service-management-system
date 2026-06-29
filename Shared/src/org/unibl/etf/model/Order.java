package org.unibl.etf.model;

import java.util.List;
import java.util.Objects;

public class Order {
	private static int ORDER_COUNT = 1;
	
	private int id;
    private Supplier supplier;
    private List<Part> parts;
    private OrderStatus status;

    public Order() {
    	super();
    	this.id = ORDER_COUNT++;
    }

	public Order(Supplier supplier, List<Part> parts, OrderStatus status) {
		this();
		this.supplier = supplier;
		this.parts = parts;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
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
		Order other = (Order) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", supplier=" + supplier + ", parts=" + parts + ", status=" + status + "]";
	}    
}
