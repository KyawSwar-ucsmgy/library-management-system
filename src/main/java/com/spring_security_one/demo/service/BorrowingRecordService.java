package com.spring_security_one.demo.service;



import java.time.LocalDate;
import java.util.List;

import com.spring_security_one.demo.model.BorrowingRecord;
import com.spring_security_one.demo.model.BorrowingRecordDto;

public interface BorrowingRecordService {

	void borrowBook(Long bookId, String email);

	List<BorrowingRecord> getAllBorrowingRecords();
   BorrowingRecord getBorrowingRecordById(Long id);
//    BorrowingRecord updateBorrowingRecord(BorrowingRecord borrowingRecord);
//    long calculateFine(Long id);
//
//    void updateBorrowingRecord(BorrowingRecord borrowingRecord);
   
   BorrowingRecord updateBorrowingRecord(BorrowingRecord borrowingRecord);
//  long calculateFine(LocalDate returnDate, LocalDate dueDate);
 long calculateFine(LocalDate returnDate, LocalDate dueDate, Long id);

List<BorrowingRecord> getBorrowingRecordsByUserEmail(String email);

	

	
//	List<BorrowingRecord> getAllBorrowingRecords();
//    BorrowingRecord getBorrowingRecordById(Long id);
//    BorrowingRecord saveBorrowingRecord(BorrowingRecordDto borrowingRecordDto);
//    BorrowingRecord updateBorrowingRecord(Long id, BorrowingRecordDto borrowingRecordDto);
    void deleteBorrowingRecord(Long id);

}
