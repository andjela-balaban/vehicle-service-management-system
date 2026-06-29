package org.unibl.etf.chat.server;

import org.unibl.etf.dto.*;
import org.unibl.etf.properties.ConfigManager;
import java.io.*;
import java.util.*;
import javax.net.ssl.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import static org.unibl.etf.logger.MyLogger.logger;

public class ChatServer {
    private static ChatServer instance = null;
    private static int PORT = ConfigManager.CHAT_PORT; // TCP port
    private static Map<Integer, ClientHandlerThread> activeClients = new ConcurrentHashMap<>();
	private static String KEY_STORE_PATH = ConfigManager.KEY_STORE_PATH;
	private static String KEY_STORE_PASSWORD = ConfigManager.KEY_STORE_PASSWORD;

	private ChatServer() {}
    
    public void start() throws IOException {
    	System.setProperty("javax.net.ssl.keyStore", KEY_STORE_PATH);
        System.setProperty("javax.net.ssl.keyStorePassword", KEY_STORE_PASSWORD);

        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(PORT);
        
        new Thread(() -> {
            try {
                System.out.println("ChatServer started...");
                while (true) {
                    SSLSocket s = (SSLSocket) ss.accept();
                    new ClientHandlerThread(s).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.log(Level.WARNING, "Exception: ", e);
            }
        }).start();
    }

    public static synchronized ChatServer getInstance() {
        if (instance == null) {
        	instance = new ChatServer();
        }
        return instance;
    }

    public static void addClient(int userId, ClientHandlerThread handler) {
        activeClients.put(userId, handler);
        System.out.println("Client " + userId + " connected.");
    }

    public static void removeClient(int userId) {
        activeClients.remove(userId);
        System.out.println("Client " + userId + " disconnected.");
    }

    public static void routeMessage(ChatMessage msg) {
        if (msg.getType() == MessageType.UNICAST) {
            int receiverId = Integer.parseInt(msg.getReceiverId());
            ClientHandlerThread handler = activeClients.get(receiverId);
            if (handler != null) {
            	handler.sendMessage(msg);            
            }
        } 
        else if (msg.getType() == MessageType.MULTICAST) {
        	for (ClientHandlerThread ch : activeClients.values()) {
                if (ch.getClientId() != msg.getSenderId()) {
                    ch.sendUDPMessage(msg);
                }
            }
        }
    }

	public static Map<Integer, ClientHandlerThread> getActiveClients() {
		return activeClients;
	}
}
