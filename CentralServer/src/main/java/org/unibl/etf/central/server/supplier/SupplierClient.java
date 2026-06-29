package org.unibl.etf.central.server.supplier;

import java.util.*;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import javax.net.ssl.SSLSocket;
import org.unibl.etf.properties.ConfigManager;
import org.unibl.etf.model.*;
import org.unibl.etf.exception.*;

public class SupplierClient {

	private static String KEY_STORE_PATH = ConfigManager.KEY_STORE_PATH;
	private static String KEY_STORE_PASSWORD = ConfigManager.KEY_STORE_PASSWORD;
	
	
	public List<Item> getOffer(Supplier chosen) throws Exception {
		if(chosen == null)
			throw new SupplierDoesNotExist("The selected supplier does not exist.");
		
	    try (SSLSocket socket = createSocket(chosen)) {
	        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

	        out.writeObject("offer_list");
	        out.flush();

	        @SuppressWarnings("unchecked")
	        List<Item> items = (List<Item>) in.readObject();
	        if(items == null) {
				throw new NoItemsAvailableException("The supplier currently has no available items in stock.");
			}
	        return items;
	    }
	}
	
	public int sendOrder(Supplier chosen, Item item, int quantity) throws Exception {
		if(chosen == null)
			throw new SupplierDoesNotExist("The selected supplier does not exist.");
	
	    try (SSLSocket socket = createSocket(chosen)) {
	        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
	        
	        out.writeObject("new_order");
	        out.flush();

	        Order order = new Order(chosen, item, quantity, OrderStatus.PENDING);
	        out.writeObject(order);
	        out.flush();
	        
	        int orderId = (Integer)in.readObject();	// confirmation that the order has been sent
	        return orderId;
	    }
	}
	
	public Order getOrderStatus(Supplier chosen, int id) throws Exception {
		if(chosen == null)
			throw new SupplierDoesNotExist("The selected supplier does not exist.");
	
	    try (SSLSocket socket = createSocket(chosen)) {
	        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

	        out.writeObject("order_status");
	        out.flush();
	    
	        out.writeObject(id);
	        out.flush();

	        return (Order) in.readObject();
	    }
	}
	
	private SSLSocket createSocket(Supplier chosen) throws Exception {
		System.setProperty("javax.net.ssl.trustStore", KEY_STORE_PATH);
        System.setProperty("javax.net.ssl.trustStorePassword", KEY_STORE_PASSWORD);
        
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
		return (SSLSocket) sf.createSocket(chosen.getHost(), chosen.getPort());
	}
	
}
