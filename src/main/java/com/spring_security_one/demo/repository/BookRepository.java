package com.spring_security_one.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring_security_one.demo.model.Book;


//import com.groupone.library.model.Staff;

public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findById(Long id);

	void deleteById(Long id);
	
    @Query("SELECT SUM(b.quantity) FROM Book b")
    Long getTotalBookQuantity();
    
    @Query("SELECT b FROM Book b JOIN FETCH b.author JOIN FETCH b.category")
    List<Book> findAllWithAuthorAndCategory();
    
    @Query("SELECT book FROM Book book where CONCAT(book.title, '', book.author.name, '', book.category.categories_type) LIKE %?1%")
    public List<Book> search(String keyword);
    public Book findByTitle(String title);
    
    


}
