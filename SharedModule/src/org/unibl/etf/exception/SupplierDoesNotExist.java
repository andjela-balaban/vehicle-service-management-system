package org.unibl.etf.exception;

public class SupplierDoesNotExist extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SupplierDoesNotExist() {
		super();
	}
	
	public SupplierDoesNotExist(String message) {
		super(message);
	}
}
