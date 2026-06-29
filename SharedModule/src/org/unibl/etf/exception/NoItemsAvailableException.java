package org.unibl.etf.exception;

public class NoItemsAvailableException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoItemsAvailableException() {
		super();
	}
	
	public NoItemsAvailableException(String message) {
		super(message);
	}
}
