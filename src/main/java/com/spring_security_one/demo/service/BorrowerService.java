package com.spring_security_one.demo.service;

import java.util.List;

import com.spring_security_one.demo.model.Borrower;
import com.spring_security_one.demo.model.BorrowerDto;

public interface BorrowerService {
    List<Borrower> getAllBorrowers();

	void createBorrower(BorrowerDto borrowerDto);

	Borrower getBorrowerById(Long id);
	
	List<Borrower> getBorrowerByEmail(String id);

	void editBorrower(Long id, BorrowerDto borrowerDto);

	void deleteBorrower(Long id);

	long borrowerCount();

	void borrowBook(Long bookId, Long borrowerId);
	
	
	
	
	
	//////////testing borrower view
//	public void borrowBook(Long bookId, Long borrowerId);
	
//	public Borrower findByEmailAndPassword(String email, String password);
//	
//	public Borrower findById(Long id);
//	
//	public void borrowBook(Long bookId, Long borrowerId);
//	
	
    
}
