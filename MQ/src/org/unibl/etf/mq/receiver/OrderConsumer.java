package org.unibl.etf.mq.receiver;

import org.unibl.etf.mq.connection.ConnectionFactoryUtil;
import org.unibl.etf.properties.ConfigManager;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.GetResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.unibl.etf.model.Order;


public class OrderConsumer {

    private final static String QUEUE_NAME = ConfigManager.QUEUE_NAME;

    public static Order getNextOrder() throws Exception {
        try (Connection connection = ConnectionFactoryUtil.createConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            GetResponse response = channel.basicGet(QUEUE_NAME, true); 
            if (response == null) {
                return null; // no messages
            }

            String json = new String(response.getBody(), "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, Order.class);
        }
    }
}
