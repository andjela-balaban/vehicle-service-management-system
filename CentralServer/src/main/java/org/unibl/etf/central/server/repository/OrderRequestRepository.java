package org.unibl.etf.central.server.repository;

import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.dto.*;

public class OrderRequestRepository {
	private List<OrderRequestDTO> orders = new ArrayList<>();
	private static OrderRequestRepository instance = null;
	
	private OrderRequestRepository() {
		super();
	}
	
	public List<OrderRequestDTO> getOrders(){
		return orders;
	}
	
	public static OrderRequestRepository getInstance() {
		if(instance == null) {
    		instance = new OrderRequestRepository();
    	}
    	return instance;
	}
}
