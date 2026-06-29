package org.unibl.etf.central.server.dto;

public class ErrorMessage extends ResponseMessage {

	public ErrorMessage() {
		super();
	}
	
	public ErrorMessage(String message) {
		super(message);
	}

	public String getMessage() {
		return super.getMessage();
	}

	public void setMessage(String message) {
		super.setMessage(message);
	}
}
