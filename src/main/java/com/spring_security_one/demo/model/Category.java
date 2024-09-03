package com.spring_security_one.demo.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long  id;
	
	private String categories_type;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Book> books;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategories_type() {
		return categories_type;
	}

	public void setCategories_type(String categories_type) {
		this.categories_type = categories_type;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	

	public Category(long id, String categories_type, List<Book> books) {
		super();
		this.id = id;
		this.categories_type = categories_type;
		this.books = books;
	}

	public Category() {
		super();
	}
    
	
}
