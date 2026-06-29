package org.unibl.etf.session;

public class UserSession {
    private static UserSession instance;

    private int clientId;
    private String username;

    private UserSession(int clientId, String username) {
        this.clientId = clientId;
        this.username = username;
    }

    public static void init(int clientId, String username) {
        instance = new UserSession(clientId, username);
    }

    public static UserSession getInstance() {
        return instance;
    }

    public int getClientId() {
        return clientId;
    }

    public String getUsername() {
        return username;
    }

    public static void clear() {
        instance = null;
    }
}
