package org.unibl.etf.supplier.repository;

import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.model.*;

public class OrderRepository {
	private List<Order> orders = new ArrayList<>();
	private static OrderRepository instance = null;
	
	private OrderRepository() {
		super();
	}
	
	public boolean addOrder(Order order) {
		return orders.add(order);
	}
	
	public boolean removeOrder(Order order) {
		return orders.remove(order);
	}
	
	public static OrderRepository getInstance() {
		 if(instance == null) 
			 instance = new OrderRepository();
		 return instance; 
	}
	
	public List<Order> getOrders(){
		return orders;
	}
}