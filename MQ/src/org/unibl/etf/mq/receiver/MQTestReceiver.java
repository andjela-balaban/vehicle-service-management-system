package org.unibl.etf.mq.receiver;

import org.unibl.etf.model.Order;

public class MQTestReceiver {
    public static void main(String[] argv) throws Exception {
    	for(int i = 0; i< 3; i++) {
    		Order order = OrderConsumer.getNextOrder();
    		System.out.println(order);
    		Thread.sleep(2000);
    	}
    }
}
