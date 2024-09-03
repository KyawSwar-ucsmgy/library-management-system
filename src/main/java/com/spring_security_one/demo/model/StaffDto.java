package com.spring_security_one.demo.model;

import jakarta.validation.constraints.NotEmpty;

public class StaffDto {
	
	@NotEmpty(message="The name is required")
	private String name;
	
	@NotEmpty(message="The address is required")
	private String address;
	
	@NotEmpty(message="The phno is required")
	private String phno;
	
	@NotEmpty(message="The email is required.")
	private String email;
	
	@NotEmpty(message = "The password is required.")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
}

