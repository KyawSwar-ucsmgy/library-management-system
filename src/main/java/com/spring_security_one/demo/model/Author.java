package com.spring_security_one.demo.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="authors")

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String bio;
    
    @OneToMany (mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;
    
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
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public Author(Long id, String name, String bio) {
		super();
		this.id = id;
		this.name = name;
		this.bio = bio;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public Author(Long id, String name, String bio, List<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.bio = bio;
		this.books = books;
	}
	public Author() {
		super();
	}	
	
}







