package com.spring_security_one.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring_security_one.demo.model.Author;

//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.groupone.library.model.Author;


public interface AuthorRepository extends JpaRepository<Author,Long >{

	
	
	@Query ("SELECT author From Author author WHERE CONCAT(author.id,'',author.name)LIKE %?1%")
	public List<Author> search(String keyword);
	public Author findByName(String name);

}
