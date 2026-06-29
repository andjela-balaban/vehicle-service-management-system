package org.unibl.etf.model;

import java.io.Serializable;

public class Receipt implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int COUNT = 1;
	
	private int id;
	private Order order;
	private double price;
	
	public Receipt() {
		super();
		this.id = COUNT++;
	}
	
	public Receipt(Order order) {
		this();
		this.order = order;
		this.price = calculateTheAmount();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}
	
	private double calculateTheAmount() {
		return order.getItem().getPrice() * order.getQuantity();
	}
	
}
