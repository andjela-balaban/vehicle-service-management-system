package org.unibl.etf.mq.connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.unibl.etf.properties.ConfigManager;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionFactoryUtil {
	private static String HOST = ConfigManager.MQ_HOST;
	private static String USERNAME = ConfigManager.MQ_USERNAME;
	private static String PASSWORD = ConfigManager.MQ_PASSWORD;
	
	public static Connection createConnection() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setUsername(USERNAME);
		factory.setPassword(PASSWORD);
		
		return factory.newConnection();
	}
}
