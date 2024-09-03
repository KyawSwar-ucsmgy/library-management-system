package com.spring_security_one.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_security_one.demo.model.Staff;

public interface StaffRepository extends JpaRepository<Staff,Long>{
	
	
	
	
//	public List<Staff> search(String keyword);
//	public Staff findByName(String name);

}

