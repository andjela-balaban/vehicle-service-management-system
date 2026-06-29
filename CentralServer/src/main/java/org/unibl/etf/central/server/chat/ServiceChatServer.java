package org.unibl.etf.central.server.chat;

import org.unibl.etf.chat.server.*;

public class ServiceChatServer {
	public static void main(String[] args) throws Exception {
        ChatServer server = ChatServer.getInstance();
        server.start();
        System.out.println("Chat server started...");
    }
}
