package com.spring_security_one.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_security_one.demo.model.Borrower;
import com.spring_security_one.demo.model.BorrowerDto;
import com.spring_security_one.demo.repository.BookRepository;
import com.spring_security_one.demo.repository.BorrowerRepository;


@Service
public class BorrowerServiceImpl implements BorrowerService {

    @Autowired
    private BorrowerRepository borrowerRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
//    @Autowired
//    private BorrowingRecordRepository borrowingRecordRepository;

    

    @Override
    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }


    @Override
    public void createBorrower(BorrowerDto borrowerDto) {
        Borrower borrower = new Borrower();
        borrower.setName(borrowerDto.getName());
        borrower.setEmail(borrowerDto.getEmail());
        borrower.setPassword(borrowerDto.getPassword());
        borrower.setAddress(borrowerDto.getAddress());
        borrower.setPhone(borrowerDto.getPhone());
        borrowerRepository.save(borrower);
    }
    
    @Override
    public Borrower getBorrowerById(Long id) {
        return borrowerRepository.findById(id).orElse(null);
    }
    	@Override
    public void editBorrower(Long id, BorrowerDto borrowerDto) {
     Optional<Borrower> borrowerOptional = borrowerRepository.findById(id);
     if (borrowerOptional.isPresent()) {
    	 Borrower borrower = borrowerOptional.get();
        
        borrower.setName(borrowerDto.getName());
        borrower.setEmail(borrowerDto.getEmail());
        borrower.setPassword(borrowerDto.getPassword());
        borrower.setAddress(borrowerDto.getAddress());
        borrower.setPhone(borrowerDto.getPhone());
        borrowerRepository.save(borrower);
     }
    }
    @Override
    public void deleteBorrower(Long id) {
        borrowerRepository.deleteById(id);
    }


	@Override
	public List<Borrower> getBorrowerByEmail(String id) {
		// TODO Auto-generated method stub
		return borrowerRepository.getBorrowerByEmail(id);
	}


	@Override
	public long borrowerCount() {
		// TODO Auto-generated method stub
		return borrowerRepository.count();
	}


	@Override
	public void borrowBook(Long bookId, Long borrowerId) {
		// TODO Auto-generated method stub
		
	}

	

	
	
	/////////////////TESTING BORROWER VIEW
	
	
	
	
	
	

//	@Override
//	public void borrowBook(Long bookId, Long borrowerId) {
//		// TODO Auto-generated method stub
//		Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
//	    Borrower borrower = borrowerRepository.findById(borrowerId).orElseThrow(() -> new RuntimeException("Borrower not found"));
//	    
//	    // Create and save borrowing record
//	    BorrowingRecord record = new BorrowingRecord();
//	    record.setBook(book);
//	    record.setBorrower(borrower);
//	    record.setBorrowDate(LocalDate.now());
//	    borrowingRecordRepository.save(record);
//	}


	
	
	
//	@Override
//    public Borrower findByEmailAndPassword(String email, String password) {
//        return borrowerRepository.findByEmailAndPassword(email, password);
//    }
//
//
//	@Override
//	public Borrower findById(Long id) {
//		// TODO Auto-generated method stub
//		return borrowerRepository.findById(id).orElse(null);
//	}


//	@Override
//	public void borrowBook(Long bookId, Long borrowerId) {
//		// TODO Auto-generated method stub
//		Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
//        Borrower borrower = borrowerRepository.findById(borrowerId).orElseThrow(() -> new RuntimeException("Borrower not found"));
//        
//        if (book.getQuantity() > 0) {
//        // Create and save borrowing record
//        BorrowingRecord record = new BorrowingRecord();
//        record.setBook(book);
//        record.setBorrower(borrower);
//       // record.setBorrowDate(LocalDate.now());
//        record.setStart_date(LocalDate.now());
//        record.setDue_date(LocalDate.now().plusWeeks(2));
//        record.setStatus("Pending");
//        borrowingRecordRepository.save(record);
//
//        // Optionally, you can decrease the quantity of the book
//        book.setQuantity(book.getQuantity() - 1);
//        bookRepository.save(book);
//        } else {
//            throw new RuntimeException("Book is out of stock");
//        }
//	}
//	
  	
 }





	



