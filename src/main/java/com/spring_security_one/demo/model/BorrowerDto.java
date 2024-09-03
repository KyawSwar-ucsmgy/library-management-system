package com.spring_security_one.demo.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Pattern;

public class BorrowerDto {
	private Long id;
	
	@NotEmpty(message="This name is rerquired")
    private String name;
	@NotEmpty(message="This email is rerquired")
    private String email;
	@NotEmpty(message="This password is rerquired")
	 private String password;
	@NotEmpty(message="This address is rerquired")
    private String address;
	

	@Pattern(regexp = "^[0-9]{11}$", message = "Invalid phone number")

    private String phone;
    public BorrowerDto() {
    	
    }
    

	public BorrowerDto(Long id, String name, String email, String address, String phone,String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	  

}
