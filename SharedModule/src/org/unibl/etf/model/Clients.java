package org.unibl.etf.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "clients")
@XmlAccessorType(XmlAccessType.FIELD)
public class Clients {

    @XmlElement(name = "client")
    private List<Client> clients = new ArrayList<>();

    public Clients() {
    	super();
    }

    public Clients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
