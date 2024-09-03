package com.spring_security_one.demo.model;

public class AuthorDto {

	private String name;
    private String bio;
       
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	
	
	public AuthorDto( String name, String bio) {
		super();
		this.name = name;
		this.bio = bio;
	}
	public AuthorDto() {
		super();
	}

}
