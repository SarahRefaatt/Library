package com.example.demo.security;

import lombok.Data;

@Data
public class RegisterDto {
	
	String name;
	String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
