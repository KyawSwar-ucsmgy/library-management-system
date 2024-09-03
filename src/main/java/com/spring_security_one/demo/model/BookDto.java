package com.spring_security_one.demo.model;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class BookDto {
	
	@NotEmpty(message = "The title is required")
    private String title;
    
	//@NotEmpty(message = "The quantity is required")
	
    private int quantity;
	
	@Min(0)
    private double borrow_price;
	
	private String image;
	
	
	private MultipartFile imageFile;
	
    private String imageFileName;
    private LocalDateTime createdAt;
    
    private Author author;
    
    private Category category;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getBorrow_price() {
		return borrow_price;
	}

	public void setBorrow_price(double borrow_price) {
		this.borrow_price = borrow_price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BookDto(@NotEmpty(message = "The title is required") String title,
			 int quantity, @Min(0) double borrow_price, String image,
			MultipartFile imageFile, String imageFileName, LocalDateTime createdAt, Author author, Category category) {
		super();
		this.title = title;
		this.quantity = quantity;
		this.borrow_price = borrow_price;
		this.image = image;
		this.imageFile = imageFile;
		this.imageFileName = imageFileName;
		this.createdAt = createdAt;
		this.author = author;
		this.category = category;
	}

	public BookDto() {
		super();
	}

	
    

}
