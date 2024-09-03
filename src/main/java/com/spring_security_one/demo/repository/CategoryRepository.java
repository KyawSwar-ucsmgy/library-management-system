package com.spring_security_one.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_security_one.demo.model.Category;



public interface CategoryRepository extends JpaRepository<Category, Long> {

	
}
