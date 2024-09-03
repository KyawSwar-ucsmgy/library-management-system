package com.spring_security_one.demo.model;

public class CategoryDto {

	private String categories_type;

	public String getCategories_type() {
		return categories_type;
	}

	public void setCategories_type(String categories_type) {
		this.categories_type = categories_type;
	}

	public CategoryDto(String categories_type) {
		super();
		this.categories_type = categories_type;
	}

	public CategoryDto() {
		super();
	}
	
	
}
