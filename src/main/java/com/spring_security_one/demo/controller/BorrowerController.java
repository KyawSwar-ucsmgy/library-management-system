package com.spring_security_one.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring_security_one.demo.model.Author;
import com.spring_security_one.demo.model.Book;
import com.spring_security_one.demo.model.BorrowingRecord;
import com.spring_security_one.demo.model.Category;
import com.spring_security_one.demo.model.User;
import com.spring_security_one.demo.security.LoginUserDetail;
import com.spring_security_one.demo.service.AuthorService;
import com.spring_security_one.demo.service.BookService;
import com.spring_security_one.demo.service.BorrowerService;
import com.spring_security_one.demo.service.BorrowingRecordService;
import com.spring_security_one.demo.service.CategoryService;

//import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
@RequestMapping("/user")
public class BorrowerController {
	 
	    @Autowired
	    private BookService bookService;
	    
	    @Autowired
	    private CategoryService categoryService;
	    
	    
	    @Autowired
	    private AuthorService authorService;
	    

	    @GetMapping({"", "/"})
	    public String borrowBook(Model model, @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
	        List<Book> books = bookService.getAllBooks(keyword);	
	        //List<Book> keyword = bookService.getAllBooks(keyword);	
	        model.addAttribute("keyword", keyword);
	        model.addAttribute("books", books);
	        return "user/index";
	    }
	    
	    @Autowired
	    private BorrowingRecordService borrowingRecordService;
	    
	    @PostMapping("/borrow/{bookId}")
	    public String borrowBook(@PathVariable("bookId") Long bookId, @AuthenticationPrincipal UserDetails userDetails) {
	    	
	    	String email = userDetails.getUsername();
	    	borrowingRecordService.borrowBook(bookId, email);
	    	return "redirect:/user";
	    }
	    

	   
	    
	    @GetMapping("/contact")
	    private String contactPage() {
	    	
	    	return "/user/contact";
	    }
	    
	   

//	    @GetMapping("/record")
//	    public String viewBorrowingRecords(Model model, Authentication authentication) {
//	    	
//	    	
//	    	LoginUserDetail userDetail =(LoginUserDetail) authentication.getPrincipal();
//	    	String user = userDetail.getUsername();
//	    
//	        List<BorrowingRecord> borrowingRecords = borrowingRecordService.getAllBorrowingRecords();
//	        
//	        model.addAttribute("user_id", user_id);
//	        model.addAttribute("borrowingRecords", borrowingRecords);
//	        return "user/record";
//	    }
//	    
	    
	    @GetMapping("/record")
	    public String viewBorrowerBorrowingRecords(@AuthenticationPrincipal UserDetails userDetails, Model model) {
	        String email = userDetails.getUsername();
	        List<BorrowingRecord> borrowingRecords = borrowingRecordService.getBorrowingRecordsByUserEmail(email);
	        model.addAttribute("borrowingRecords", borrowingRecords);
	        return "user/record";
	    }
	    
	    
	    
	    @GetMapping("/category")
		private String categoryDashBoard(Model model) {

			List<Category> categories = categoryService.getAllCategories();
			model.addAttribute("categories", categories);

			return "user/category";
		}
	    
	    @GetMapping("/author")
		private String authorDashBoard(Model model, @Param("keyword") String keyword) {

			List<Author> authors = authorService.getAllAuthor();
			model.addAttribute("author", authors);

			return "user/author";
		}
	    
	    
	    
	    
//	    @GetMapping
	    
//	    @PostMapping("/borrower/login")
//	    public String login(@RequestParam String email, @RequestParam String password, Model model) {
//	        Borrower borrower = borrowerService.findByEmailAndPassword(email, password);
//	        if (borrower != null) {
//	            model.addAttribute("borrower", borrower);
//	            model.addAttribute("books", bookService.getAllBooks());
//	            return "borrowerHome";
//	        } else {
//	            model.addAttribute("error", "Invalid email or password");
//	            return "borrowerLogin";
//	        }
//	    }
//	    
    
//	    
//	    @PostMapping("/borrower/borrow")
//	    public String borrowBook(@RequestParam Long bookId, @RequestParam Long borrowerId, Model model) {
//	        borrowerService.borrowBook(bookId, borrowerId);
//	        model.addAttribute("borrower", borrowerService.findById(borrowerId));
//	        model.addAttribute("books", bookService.findAll());
//	        return "borrowerHome";
//	    }

}

