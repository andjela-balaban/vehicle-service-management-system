package org.unibl.etf.central.server.service;

import java.util.List;
import org.unibl.etf.dto.*;
import org.unibl.etf.central.server.repository.*;
import org.unibl.etf.model.Client;
import org.unibl.etf.model.ClientStatus;
import org.unibl.etf.model.Vehicle;

public class ClientService {
	
	private final ClientRepository clientRepository;

	public ClientService() {
		clientRepository = ClientRepository.getInstance();
	}
	
    public List<Client> getAllClients() {
    	return clientRepository.getClients();
    }
    
    
    public Client findById(int id) {
        return clientRepository.getClients()
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public Client findByUsername(String username) {
        return clientRepository.getClients()
                .stream()
                .filter(c -> c.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
    
    // client login validation
    public Client authenticateLogin(LoginDTO loginDto) {
    	Client client = findByUsername(loginDto.getUsername());
    	if(client != null && client.getStatus() == ClientStatus.ACTIVE && client.getPassword().equals(loginDto.getPassword()))
    		return client;
    	return null;
    }
    
    // during registration we create a new client 
    public boolean createClient(RegistrationDTO registrationDto) {
    	String firstName = registrationDto.getFirstName();
    	String lastName = registrationDto.getLastName();
    	Vehicle vehicle = registrationDto.getVehicle();
    	String address = registrationDto.getAddress();
    	String phone = registrationDto.getPhoneNumber();
    	String email = registrationDto.getEmail();
    	String username = registrationDto.getUsername();
    	String password = registrationDto.getPassword();
    	if(findByUsername(username) != null)	// there is a client with the given username, so we cannot create a new client
    		return false;
    	return addClient(new Client(firstName, lastName, vehicle, address, phone, email, username, password, ClientStatus.PENDING));
    }

    public Client removeClient(int id) {
    	Client client = findById(id);
        if (client != null) {
        	deleteClient(client.getId());
        	return client;
        }
        return null;
    }
    
    public boolean updateClient(int id, Client updatedClient) {
        Client existing = findById(id);
        if (existing == null) {
        	return false;
        }

        int index = clientRepository.getClients().indexOf(existing);
        clientRepository.getClients().set(index, updatedClient);
        clientRepository.save();
        return true;
    }

    public boolean approveClientAccount(int id) {
    	Client client = findById(id);
    	if(client != null) {
    		client.setStatus(ClientStatus.ACTIVE);
    		return updateClient(client.getId(), client);
    	}
    	return false;
    }
    
    public boolean blockClientAccount(int id) {
    	Client client = findById(id);
    	if(client != null) {
    		client.setStatus(ClientStatus.BLOCKED);
    		return updateClient(client.getId(), client);
    	}
    	return  false;
    }
    
    private boolean addClient(Client client) {
    	client.setStatus(ClientStatus.PENDING);
        boolean result = clientRepository.getClients().add(client);
        clientRepository.save();
        return result;
    }

    private boolean deleteClient(int id) {
    	boolean result = clientRepository.getClients().removeIf(c -> c.getId() == id);
    	clientRepository.save();
        return result;
    }
}
