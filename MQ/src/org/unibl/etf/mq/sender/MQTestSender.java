package org.unibl.etf.mq.sender;
import org.unibl.etf.model.*;

public class MQTestSender {
    public static void main(String[] argv) throws Exception {
    	Order order = new Order(new Supplier("supplier", "localhost", 9001), new Item(), 3, OrderStatus.PENDING);
        OrderProducer.sendOrder(order);
        Thread.sleep(2000);
        OrderProducer.sendOrder(order);
        Thread.sleep(2000);
        OrderProducer.sendOrder(order);
        Thread.sleep(2000);
        OrderProducer.sendOrder(order);
        OrderProducer.sendOrder(order);
//        for(int i = 0; i < 0; i++) {
//        	OrderProducer.sendOrder(order);
//        	Thread.sleep(2000);
//        }
    }
}
