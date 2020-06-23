package com.flightInvetory.api.customer.userAuth;

public class AuthenticationRequest {
	private String userName;
	private String password;
	
	
	public AuthenticationRequest() {
		
	}
	
	public AuthenticationRequest(String username, String password) {
		this.userName = username;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
