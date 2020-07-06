package com.example.demo.payload;

public class LoginResponse {
  
	String token;
	String name;
	String email;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LoginResponse(String token, String name, String email) {
		super();
		this.token = token;
		this.name = name;
		this.email = email;
	}
	
	
}
