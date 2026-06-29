package org.unibl.etf.model;

import java.io.Serializable;
import java.util.Objects;

public class Supplier implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private String name;
    private String host;    
    private int port;
    
    public Supplier() {
    	super();
    }
    
	public Supplier(String name, String host, int port) {
		this();
		this.name = name;
		this.host = host;
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supplier other = (Supplier) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return name;
	}
}
