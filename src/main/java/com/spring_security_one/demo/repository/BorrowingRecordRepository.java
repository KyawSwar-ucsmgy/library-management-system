package com.spring_security_one.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring_security_one.demo.model.BorrowingRecord;
import com.spring_security_one.demo.model.User;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
	
	List<BorrowingRecord> findByUser(User user);
}
