package com.example.demo.model;

public class AuthenticationResponse {
	
	private String username;
	private String message;
	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthenticationResponse(String username, String message) {
		super();
		this.username = username;
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
