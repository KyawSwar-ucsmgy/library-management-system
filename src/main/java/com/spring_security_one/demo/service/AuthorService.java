package com.spring_security_one.demo.service;

import java.util.List;

import com.spring_security_one.demo.model.Author;
import com.spring_security_one.demo.model.AuthorDto;






public interface AuthorService {
    void createAuthor(AuthorDto authorDto);
    Author getAuthorById(Long id);
    
    void deleteAuthor(Long id);


	void editAuthor(Long id, AuthorDto authorDto);
	List<Author> searchAuthors(String keyword);
	List<Author> getAllAuthor();
}

