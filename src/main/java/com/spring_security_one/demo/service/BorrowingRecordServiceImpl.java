package com.spring_security_one.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_security_one.demo.model.Book;
import com.spring_security_one.demo.model.BorrowingRecord;
import com.spring_security_one.demo.model.BorrowingRecordDto;
import com.spring_security_one.demo.model.User;
import com.spring_security_one.demo.repository.BookRepository;
import com.spring_security_one.demo.repository.BorrowingRecordRepository;
import com.spring_security_one.demo.repository.UserRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookRepository bookRepository;

//	@Override
//	public void borrowBook(Long bookId, String email) {
//		// TODO Auto-generated method stub
//		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
//		Book book= bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
//		
//		
//		
//		
//		BorrowingRecord borrowingRecord = new BorrowingRecord();
//		borrowingRecord.setUser(user);
//		borrowingRecord.setBook(book);
//		borrowingRecord.setStartDate(LocalDate.now());
//		borrowingRecord.setDueDate(LocalDate.now().plusWeeks(2));
//		borrowingRecord.setStatus("pending");
//		borrowingRecordRepository.save(borrowingRecord);
//	}
//		
		@Override
	    public void borrowBook(Long bookId, String email) {
	        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

	        if (book.getQuantity() > 0) {
	            BorrowingRecord borrowingRecord = new BorrowingRecord();
	            borrowingRecord.setUser(user);
	            borrowingRecord.setBook(book);
	            borrowingRecord.setStartDate(LocalDate.now());
	            borrowingRecord.setDueDate(LocalDate.now().plusWeeks(2));
	            borrowingRecord.setStatus("pending");

	           book.setQuantity(book.getQuantity() - 1);
	           
	            bookRepository.save(book);

	            borrowingRecordRepository.save(borrowingRecord);
	        } else {
	            throw new RuntimeException("Book is out of stock");
	        }
	    }

	    @Override
	    public BorrowingRecord getBorrowingRecordById(Long id) {
	        return borrowingRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("BorrowingRecord not found"));
	    }
//
//	 
	    
	//    ORIGINAL
	    @Override
	    public BorrowingRecord updateBorrowingRecord(BorrowingRecord borrowingRecord) {
	        return borrowingRecordRepository.save(borrowingRecord);
	    }
//	    
//	    //FINAL TESTING
//	    @Override
//	    public BorrowingRecord updateBorrowingRecord(BorrowingRecord borrowingRecord) {
//	        BorrowingRecord existingRecord = getBorrowingRecordById(borrowingRecord.getId());
//	        
//	        if ("returned".equals(borrowingRecord.getStatus()) && "pending".equals(existingRecord.getStatus())) {
//	            Book book = existingRecord.getBook();
//	            book.setQuantity(book.getQuantity() + 1); // Increment the quantity when the book is returned
//	            bookRepository.save(book);
//	        }
//	        
//	        return borrowingRecordRepository.save(borrowingRecord);
//	    }

	    //ORIGINAL

	    @Override
	    public List<BorrowingRecord> getAllBorrowingRecords() {
	        return borrowingRecordRepository.findAll();
	    }

	    
	   
//	    @Override
//	    public long calculateFine(LocalDate returnDate, LocalDate dueDate) {
//	        if (returnDate != null && returnDate.isAfter(dueDate)) {
//	        	
//	            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
//	            return (int) daysLate * 50;
//	        }
//	        return 0;
//	    }
	    
	    //END ORIGINAL
	    
//	    TESTING FINE
	    
	   @Override
	  public long calculateFine(LocalDate returnDate, LocalDate dueDate, Long id) {
	        if (returnDate != null && returnDate.isAfter(dueDate)) {
	        	
	        	BorrowingRecord borrowingRecord = getBorrowingRecordById(id);
	        	int book_quantity = borrowingRecord.getBook().getQuantity();
	        	double borrow_price = borrowingRecord.getBook().getBorrow_price();
	        	
	            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
	            return (int) ((daysLate * 50 * book_quantity) + (book_quantity * borrow_price));
	        }
	        return 0;
	    }

	    
	 

	    
	

		@Override
		public List<BorrowingRecord> getBorrowingRecordsByUserEmail(String email) {
			// TODO Auto-generated method stub
			User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	        return borrowingRecordRepository.findByUser(user);
		}

		@Override
		public void deleteBorrowingRecord(Long id) {
			// TODO Auto-generated method stub
			borrowingRecordRepository.deleteById(id);
		}

		
		

		
	   
	
	}

//    @Override
//    public List<BorrowingRecord> getAllBorrowingRecords() {
//        return borrowingRecordRepository.findAll();
//    }
//
//    @Override
//    public BorrowingRecord getBorrowingRecordById(Long id) {
//        return borrowingRecordRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public BorrowingRecord saveBorrowingRecord(BorrowingRecordDto borrowingRecordDto) {
//        User user = userService.getUserById(borrowingRecordDto.getUserId());
//        Book book = bookRepository.findById(borrowingRecordDto.getBookId()).orElse(null);
//
//        if (user != null && book != null && book.getQuantity() > 0) {
//            BorrowingRecord borrowingRecord = new BorrowingRecord();
//            borrowingRecord.setUser(user);
//            borrowingRecord.setBook(book);
//            borrowingRecord.setStartDate(LocalDate.now());
//            borrowingRecord.setDueDate(LocalDate.now().plusWeeks(2));
//            borrowingRecord.setStatus("Pending");
//
//            book.setQuantity(book.getQuantity() - 1);
//            bookRepository.save(book);
//
//            return borrowingRecordRepository.save(borrowingRecord);
//        }
//        return null;
//    }
//
//    @Override
//    public BorrowingRecord updateBorrowingRecord(Long id, BorrowingRecordDto borrowingRecordDto) {
//        BorrowingRecord existingRecord = borrowingRecordRepository.findById(id).orElse(null);
//        if (existingRecord != null) {
//            existingRecord.setReturnDate(borrowingRecordDto.getReturnDate());
//            existingRecord.setStatus(borrowingRecordDto.getStatus());
//            return borrowingRecordRepository.save(existingRecord);
//        }
//        return null;
//    }
//
