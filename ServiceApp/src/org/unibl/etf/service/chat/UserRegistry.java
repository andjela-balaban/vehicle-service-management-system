package org.unibl.etf.service.chat;

import java.util.*;
import org.unibl.etf.model.Client;
import org.unibl.etf.service.rest.ClientRESTController;

public class UserRegistry {
    private Map<Integer, String> idToUsername;
    private Map<String, Integer> usernameToId;
    private List<Client> clients;
    private static UserRegistry instance = null;

    private UserRegistry() {
        clients = ClientRESTController.getAllClients(); 
        idToUsername = new HashMap<>();
        usernameToId = new HashMap<>();

        for(Client client : clients) {
            addUser(client.getId(), client.getUsername());
        }
        // “Repairman” has ID = 0
        addUser(0, "Repairman");
    }

    public void addUser(int id, String username) {
        idToUsername.put(id, username);
        usernameToId.put(username, id);
    }

    public void removeUser(int id) {
        String username = idToUsername.remove(id);
        if (username != null) {
            usernameToId.remove(username);
        }
    }

    public String getUsername(int id) {
        return idToUsername.get(id);
    }

    public int getId(String username) {
        return usernameToId.getOrDefault(username, -1);
    }

    public List<String> getAllUsernames() {
        return new ArrayList<>(usernameToId.keySet());
    }

    public Client findById(int id) {
        return clients.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public Client findByUsername(String username) {
        return clients.stream().filter(c -> c.getUsername().equals(username)).findFirst().orElse(null);
    }

    public static UserRegistry getInstance() {
        if (instance == null) {
            instance = new UserRegistry();
        }
        return instance;
    }
}
