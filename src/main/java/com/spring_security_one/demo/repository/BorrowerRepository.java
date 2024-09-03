package com.spring_security_one.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring_security_one.demo.model.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower,Long> {
	
		@Query("SELECT r FROM Borrower r WHERE r.email = :email")
		List<Borrower> getBorrowerByEmail(@Param("email") String email);
		
		
		/////////BORROWER VIEW TESTING
		Borrower findByEmailAndPassword(String email, String password);
}
