package com.spring_security_one.demo.model;

import java.sql.Date;
import java.util.Set;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int quantity;
    private double borrow_price;
   
    @Column
    private Date createdAt;
    private String imageFileName;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "author_id")
    private Author author;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @OneToMany(mappedBy = "book")
    private Set<BorrowingRecord> borrowingRecords;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
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

	

	public Book() {
		super();
	}

	public Set<BorrowingRecord> getBorrowingRecords() {
		return borrowingRecords;
	}

	public void setBorrowingRecords(Set<BorrowingRecord> borrowingRecords) {
		this.borrowingRecords = borrowingRecords;
	}

	public Book(Long id, String title, int quantity, double borrow_price, Date createdAt, String imageFileName,
			Author author, Category category, Set<BorrowingRecord> borrowingRecords) {
		super();
		this.id = id;
		this.title = title;
		this.quantity = quantity;
		this.borrow_price = borrow_price;
		this.createdAt = createdAt;
		this.imageFileName = imageFileName;
		this.author = author;
		this.category = category;
		this.borrowingRecords = borrowingRecords;
	}

	
    
//    @OneToMany(mappedBy = "book")
//    private Set<BorrowingRecord> borrowingRecords;
//    
//    public Set<BorrowingRecord> getBorrowingRecords() {
//		return borrowingRecords;
//	}
//
//	public void setBorrowingRecords(Set<BorrowingRecord> borrowingRecords) {
//		this.borrowingRecords = borrowingRecords;
//	}

	


	
    
}
