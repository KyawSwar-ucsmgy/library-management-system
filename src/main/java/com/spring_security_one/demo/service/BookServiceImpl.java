package com.spring_security_one.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring_security_one.demo.model.Book;
import com.spring_security_one.demo.model.BookDto;
import com.spring_security_one.demo.model.Category;
import com.spring_security_one.demo.repository.AuthorRepository;
import com.spring_security_one.demo.repository.BookRepository;
import com.spring_security_one.demo.repository.CategoryRepository;



@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repo;

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Book> getAllBooks() {
      //  return repo.findAll();
        return repo.findAllWithAuthorAndCategory();
    }


    
    
    
    
    @Override
    public void createBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setQuantity(bookDto.getQuantity());
        book.setBorrow_price(bookDto.getBorrow_price());

        MultipartFile image = bookDto.getImageFile();
        String originalFileName = image.getOriginalFilename();
        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String storageFileName = generateUniqueFileName(originalFileName);
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, uploadPath.resolve(storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }

            book.setImageFileName(storageFileName);
            book.setAuthor(authorService.getAuthorById(bookDto.getAuthor().getId()));

            
            Category category = categoryService.getCategoryById(bookDto.getCategory().getId());
            if (category != null) {
                book.setCategory(category);
            } else {
                throw new RuntimeException("Category not found");
            }

            repo.save(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateUniqueFileName(String originalFileName) {
        String fileExtension = "";
        int dotIndex = originalFileName.lastIndexOf('.');
        if (dotIndex >= 0) {
            fileExtension = originalFileName.substring(dotIndex);
        }
        return System.currentTimeMillis() + fileExtension;
    }

    @Override
    public Book getBookById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void updateBook(Long id, BookDto bookDto) {
        Book existingBook = getBookById(id);
        if (existingBook != null) {
            existingBook.setTitle(bookDto.getTitle());
            existingBook.setQuantity(bookDto.getQuantity());
            existingBook.setBorrow_price(bookDto.getBorrow_price());

            MultipartFile image = bookDto.getImageFile();
            if (image != null && !image.isEmpty()) {
                String originalFileName = image.getOriginalFilename();
                String uploadDir = "public/images/";
                Path uploadPath = Paths.get(uploadDir);

                try {
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    String storageFileName = generateUniqueFileName(originalFileName);
                    try (InputStream inputStream = image.getInputStream()) {
                        Files.copy(inputStream, uploadPath.resolve(storageFileName), StandardCopyOption.REPLACE_EXISTING);
                        existingBook.setImageFileName(storageFileName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            existingBook.setAuthor(bookDto.getAuthor());
            existingBook.setCategory(bookDto.getCategory());

            repo.save(existingBook);
        }
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> optionalBook = repo.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            String imageFileName = book.getImageFileName();
            if (imageFileName != null && !imageFileName.isEmpty()) {
                try {
                    String uploadDir = "public/images/";
                    Path imagePath = Paths.get(uploadDir + imageFileName);
                    Files.deleteIfExists(imagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            repo.deleteById(id);
       }
    }

	@Override
	public long bookCount() {
		// TODO Auto-generated method stub
		return repo.count();
	}

	@Override
	public long getTotalBookQuantity() {
		// TODO Auto-generated method stub
		Long totalQuantity = repo.getTotalBookQuantity();
		return totalQuantity != null? totalQuantity : 0;
	}

	@Override
	public List<Book> getAllBooks(String keyword) {
		// TODO Auto-generated method stub
		if (keyword!=null) {
			
			return repo.search(keyword);
			
		} else {
			return (List<Book>)repo.findAll();
		}
	}






	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		repo.save(book);
	}
}
