package org.unibl.etf.chat.test;

import org.unibl.etf.chat.server.ChatServer;

public class TestServer {
    public static void main(String[] args) throws Exception {
        ChatServer server = ChatServer.getInstance();
        server.start();
        System.out.println("Server started...");
    }
}

