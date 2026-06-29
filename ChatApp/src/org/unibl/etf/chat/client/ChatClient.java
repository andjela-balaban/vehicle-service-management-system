package org.unibl.etf.chat.client;

import java.io.IOException;
import java.net.*;
import javax.net.ssl.SSLSocket;
import org.unibl.etf.properties.ConfigManager;
import org.unibl.etf.dto.*;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class ChatClient {
	private static String KEY_STORE_PATH = ConfigManager.KEY_STORE_PATH;
	private static String KEY_STORE_PASSWORD = ConfigManager.KEY_STORE_PASSWORD;
    private static final String HOST = ConfigManager.CHAT_HOST;
    private static int PORT = ConfigManager.CHAT_PORT; 
    private SSLSocket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private DatagramSocket udpSocket;
    @SuppressWarnings("unused")
	private int clientId;

    private MessageListener listener; 

    public void connect(int clientId) throws Exception {
    	System.setProperty("javax.net.ssl.trustStore", KEY_STORE_PATH);
        System.setProperty("javax.net.ssl.trustStorePassword", KEY_STORE_PASSWORD);

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) sf.createSocket(HOST, PORT);

        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());

        this.clientId = clientId;
        out.writeObject(String.valueOf(clientId));
        out.flush();

        udpSocket = new DatagramSocket(0);
        int localUdpPort = udpSocket.getLocalPort();
        out.writeObject("UDP_PORT:" + localUdpPort);
        out.flush();

        startReceivers();
    }

    private void startReceivers() {
        // TCP listener
        new Thread(() -> {
            try {
                ChatMessage msg;
                while ((msg = (ChatMessage) in.readObject()) != null) {
                    if (listener != null) 
                    	listener.onMessage(msg);
                }
            } catch (Exception e) {
                System.out.println("TCP disconnected.");
            }
        }).start();

        // UDP listener
        new Thread(() -> {
            try {
                byte[] buffer = new byte[2048];
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    udpSocket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    if (listener != null) 
                    	listener.onMulticast(received);
                }
            } catch (Exception e) {
                System.out.println("UDP listener stopped.");
            }
        }).start();
    }

    public void sendMessage(ChatMessage msg) throws IOException {
        out.writeObject(msg);
        out.flush();
    }

    public void disconnect() throws IOException {
        if (udpSocket != null) 
        	udpSocket.close();
        if (out != null) 
        	out.close();
        if (in != null) 
        	in.close();
        if (socket != null) 
        	socket.close();
    }

    public void setMessageListener(MessageListener listener) {
        this.listener = listener;
    }
}

