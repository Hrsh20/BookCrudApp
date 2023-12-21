package com.mj.exception;

@SuppressWarnings("serial")
public class ServiceLayerException extends Exception {
	
	private String message;

	public ServiceLayerException(Exception e) {
		this.message = "Exception at Service layer \n Root Cause: " + e.getLocalizedMessage();
	}

	public String getRootCause() {
		return this.message;
	}
}
