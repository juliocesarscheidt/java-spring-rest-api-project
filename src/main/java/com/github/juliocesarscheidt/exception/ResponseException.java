package com.github.juliocesarscheidt.exception;

import java.io.Serializable;
// import java.util.Date;

public class ResponseException implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String date;
	private String message;
	private String details;
	
	public ResponseException(String timestamp, String message, String details) {
		super();
		
		this.date = timestamp;
		this.message = message;
		this.details = details;
	}

	public String getDate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}	
}
