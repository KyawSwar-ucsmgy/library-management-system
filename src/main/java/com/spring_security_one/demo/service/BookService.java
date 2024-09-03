package com.spring_security_one.demo.service;


import java.util.List;

import com.spring_security_one.demo.model.Book;
import com.spring_security_one.demo.model.BookDto;




public interface BookService {
  
    void createBook(BookDto bookDto);
    Book getBookById(Long id);
    void updateBook(Long id, BookDto bookDto);
    void deleteBook(Long id);

	
	List<Book> getAllBooks();
	long bookCount();
	long getTotalBookQuantity();
	
	List<Book> getAllBooks(String keyword);
	
	 void updateBook(Book book);//addition method
}

