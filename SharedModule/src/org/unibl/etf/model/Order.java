package org.unibl.etf.model;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int ORDER_COUNT = 1;
	
	private int id;
    private Supplier supplier;
    private Item item;
    private int quantity;
    private OrderStatus status;

    public Order() {
    	super();
    	this.id = ORDER_COUNT++;
    }

	public Order(Supplier supplier, Item item, int quantity, OrderStatus status) {
		this();
		this.supplier = supplier;
		this.item = item;
		this.quantity = quantity;
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
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
		return "Order [id=" + id + ", supplier=" + supplier + ", item=" + item + ", quantity=" + quantity + ", status=" + status + "]";
	}    
}
