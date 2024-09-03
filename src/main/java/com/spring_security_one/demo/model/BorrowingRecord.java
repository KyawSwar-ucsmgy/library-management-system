package com.spring_security_one.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;
    private double fine;
    private int quantityBorrowed;
    
    
    
	public BorrowingRecord(Long id, Book book, User user, LocalDate startDate, LocalDate dueDate, LocalDate returnDate,
			String status, double fine, int quantityBorrowed) {
		super();
		this.id = id;
		this.book = book;
		this.user = user;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.status = status;
		this.fine = fine;
		this.quantityBorrowed = quantityBorrowed;
	}
	public int getQuantityBorrowed() {
		return quantityBorrowed;
	}
	public void setQuantityBorrowed(int quantityBorrowed) {
		this.quantityBorrowed = quantityBorrowed;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	public BorrowingRecord() {
		super();
	}
//	public long calculateFine() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

    
}
