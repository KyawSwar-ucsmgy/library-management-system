package com.spring_security_one.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring_security_one.demo.model.Author;
import com.spring_security_one.demo.model.AuthorDto;
import com.spring_security_one.demo.model.Book;
import com.spring_security_one.demo.model.BookDto;
import com.spring_security_one.demo.model.Borrower;
import com.spring_security_one.demo.model.BorrowerDto;
import com.spring_security_one.demo.model.BorrowingRecord;
import com.spring_security_one.demo.model.Category;
import com.spring_security_one.demo.model.CategoryDto;
import com.spring_security_one.demo.model.Staff;
import com.spring_security_one.demo.model.StaffDto;
import com.spring_security_one.demo.service.AuthorService;
import com.spring_security_one.demo.service.BookService;
import com.spring_security_one.demo.service.BorrowerService;
import com.spring_security_one.demo.service.BorrowingRecordService;
import com.spring_security_one.demo.service.CategoryService;
import com.spring_security_one.demo.service.StaffService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	 private BorrowerService borrowerService;


	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookService bookService;

	@GetMapping({ "", "/" })
	private String adminDashBoard(Model model) {
		
		long borrowerCount = borrowerService.borrowerCount();
		long bookCount = bookService.bookCount();
		long getTotalBookQuantity = bookService.getTotalBookQuantity();
		long staffCount = staffService.staffCount();
		long category = categoryService.countCategory();
		model.addAttribute("categoryCount", category);
		model.addAttribute("staffCount", staffCount);
		model.addAttribute("bookCount", bookCount);
		model.addAttribute("borrowerCount", borrowerCount);
		model.addAttribute("getTotalBookQuantity", getTotalBookQuantity);
		return "admin/index";
	}

//	@GetMapping("/staff")
//	private String staffDashBoard() {
//
//		return "admin/staff/index";
//	}
//
//	@GetMapping("/create_staff")
//	private String createStaff() {
//
//		return "admin/staff/create_staff";
//	}

//	@GetMapping("/borrower")
//	private String borrowerDashBoard() {
//
//		return "admin/borrower/index";
//	}


	@GetMapping("/category")
	private String categoryDashBoard(Model model) {

		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);

		return "admin/category/index";
	}

	@GetMapping("/create_category")
	private String createCategory(Model model) {

		CategoryDto categoryDto = new CategoryDto();
		model.addAttribute("categoryDto", categoryDto);

		return "admin/category/create_category";
	}

	@PostMapping("/create_category")
	private String postCreateCategory(@Valid @ModelAttribute("categoryDto") CategoryDto categoryDto,
			BindingResult result, Model model) {

		categoryService.createCategory(categoryDto);
		return "redirect:/admin/category";
	}

	@GetMapping("/category/edit/{id}")
	public String showEditPage(@PathVariable long id, Model model) {

		Category category = categoryService.getElementById(id);
		if (category == null) {

			return "redirect:/category";
		}
		CategoryDto categoryDto = new CategoryDto();

		categoryDto.setCategories_type(category.getCategories_type());

		model.addAttribute("categoryDto", categoryDto);
		model.addAttribute("categoryId", id);
		return "admin/category/update_category";
	}

	@PostMapping("/category/edit/{id}")
	public String updateEditPage(@PathVariable long id, @Valid @ModelAttribute("categoryDto") CategoryDto categoryDto,
			BindingResult result, Model model) {

		if (result.hasErrors()) {

			model.addAttribute("categoryId", id);
			return "/update_category";
		}
		categoryService.editCategory(id, categoryDto);
		return "redirect:/admin/category";
	}

	@GetMapping("/category/delete/{id}")
	public String deleteCategory(@PathVariable long id) {

		try {
			categoryService.deleteCategory(id);
		} catch (Exception ex) {

			System.out.println("Exception: " + ex.getMessage());
		}

		return "redirect:/admin/category";
	}

	@GetMapping("/author")
	private String authorDashBoard(Model model, @Param("keyword") String keyword) {

		List<Author> authors = authorService.getAllAuthor();
		model.addAttribute("author", authors);

		return "admin/author/index";
	}

	@GetMapping("/create_author")
	private String createAuthor(Model model) {

		AuthorDto authorDto = new AuthorDto();
		model.addAttribute("authorDto", authorDto);

		return "admin/author/create_author";
	}

	@PostMapping("/create_author")
	public String saveAuthor(@Valid @ModelAttribute("authorDto") AuthorDto authorDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "author/create_author";
		}
		authorService.createAuthor(authorDto);
		return "redirect:/admin/author";
	}

	@GetMapping("/author/edit/{id}")
	public String editAuthor(@PathVariable("id") Long id, Model model) {
		Author author = authorService.getAuthorById(id);
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName(author.getName());
		authorDto.setBio(author.getBio());
		model.addAttribute("authorDto", authorDto);
		model.addAttribute("authorId", id);
		return "/admin/author/update_author";
	}

	@PostMapping("/author/edit/{id}")
	public String editAuthor(@PathVariable("id") Long id, @Valid @ModelAttribute("authorDto") AuthorDto authorDto,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("id", id);
			model.addAttribute("authorDto", authorDto);
			return "admin/author/update_author";
		}
		authorService.editAuthor(id, authorDto);
		return "redirect:/admin/author";
	}

	@GetMapping("/author/delete/{id}")
	public String deleteAuthor(@PathVariable Long id) {
		authorService.deleteAuthor(id);
		return "redirect:/admin/author";
	}

	@GetMapping("/book")
	private String bookDashBoard(Model model) {

		List<Book> books = bookService.getAllBooks();
		model.addAttribute("book", books);

		return "admin/book/index";
	}

	@GetMapping("/create_book")
	private String createBook(Model model) {

		BookDto bookDto = new BookDto();

		List<Author> author = authorService.getAllAuthor();
		List<Category> category = categoryService.getAllCategory();
		model.addAttribute("bookDto", bookDto);
		model.addAttribute("authors", author);
		model.addAttribute("categories", category);

		return "admin/book/create_book";
	}
	
	@PostMapping("/create_book")
    public String saveBook(@Valid @ModelAttribute("bookDto") BookDto bookDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/admin/book/create_book";
            
        }
        
        if (bookDto.getImageFile().isEmpty()) {
        	
        	result.addError(new FieldError("productDto", "imageFile", "The image file is required"));
        }
        bookService.createBook(bookDto);
        return "redirect:/admin/book";
    }
	
	@GetMapping("/book/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setQuantity(book.getQuantity());
        bookDto.setBorrow_price(book.getBorrow_price());
       
        List<Author> author = authorService.getAllAuthor();
		List<Category> category = categoryService.getAllCategory();
		model.addAttribute("authors", author);
		model.addAttribute("categories", category);
        model.addAttribute("bookDto" , bookDto);
        model.addAttribute("bookId", id);
        return "/admin/book/update_book";
    }

    @PostMapping("/book/edit/{id}")
    public String updateBook(@PathVariable Long id, @Valid @ModelAttribute("bookDto") BookDto bookDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("bookDto", bookDto);
            return "/admin/book/update_book";
        }
        bookService.updateBook(id, bookDto);
        return "redirect:/admin/book";
    }

	
	@GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        
        	bookService.deleteBook(id);
       
        return "redirect:/admin/book";
    }
	
	@GetMapping("/borrower")
	   public String showBorrowerList(Model model) {
		   List<Borrower> borrower = borrowerService.getAllBorrowers();
		   model.addAttribute("borrowers",borrower);
		   return "/admin/borrower/index";	
	    }

	
	@GetMapping("/create_borrower")
    public String ShowCreatePage(Model model) {
        BorrowerDto borrowerDto = new BorrowerDto();
        model.addAttribute("borrowerDto", borrowerDto);
        return "admin/borrower/create_borrower"; // Ensure this matches the actual path
    }

	 @PostMapping("/create_borrower")
	    public String createBorrower(@ModelAttribute("borrowerDto") @Valid BorrowerDto borrowerDto,
	                               BindingResult bindingResult,Model model) {
	        if (bindingResult.hasErrors()) {
	            return "admin/borrower/create_borrower"; // Ensure this matches the actual path
	        }
	        List<Borrower> borrower =borrowerService.getBorrowerByEmail(borrowerDto.getEmail());
	        if(borrower.size()==0) {
	        	//save borrower if  borrower is not duplicate
	            borrowerService.createBorrower(borrowerDto);
	            return "redirect:/admin/borrower";
	        }
	        else {
	        	model.addAttribute("duplicateEmailError", "Email already exists.");
	        	return "admin/borrower/create_borrower"; 
	        }
	    }
	 
	 @GetMapping("/borrower/edit/{id}")
	    public String editBorrower(@PathVariable("id") long id,Model model) {
	    	Borrower borrower = borrowerService.getBorrowerById(id);
	    	BorrowerDto borrowerDto = new BorrowerDto();
	    	borrowerDto.setName(borrower.getName());
	    	borrowerDto.setEmail(borrower.getEmail());
	    	borrowerDto.setPassword(borrower.getPassword());
	    	borrowerDto.setAddress(borrower.getAddress());
	    	borrowerDto.setPhone(borrower.getPhone());
	    	model.addAttribute("borrowerDto",borrowerDto);
	    	model.addAttribute("borrowerid",id);
	    	return"admin/borrower/update_borrower";
	    }

	 @PostMapping("/borrower/edit/{id}")
	    public String updateBorrower(@PathVariable("id") long id,@Valid @ModelAttribute("borrowerDto")BorrowerDto borrowerDto,
	    		BindingResult result,Model model) {
	    	if(result.hasErrors()) {
	    		model.addAttribute("borrowerid",id);
	    		return "admin/borrower/update_borrower";
	    	}
	    	borrowerService.editBorrower(id, borrowerDto);
	    	return "redirect:/admin/borrower";
	    	
	    }
	
	    @GetMapping("/borrower/delete/{id}")
	    public String deleteBorrower(@PathVariable Long id) {
	        borrowerService.deleteBorrower(id);
	        return "redirect:/borrowers/"; // Redirect to list page after deleting
	    }
	    
	    @Autowired
	    private StaffService staffService;

	    @GetMapping("/staff")
	    public String showStaffList(Model model) {
	        List<Staff> staffs = staffService.getAllStaffs();
	        model.addAttribute("staff", staffs);
	        return "admin/staff/index";
	    }

	    @GetMapping("/create_staff")
	    public String createStaff(Model model) {
	        StaffDto staffDto = new StaffDto();
	        model.addAttribute("staffDto", staffDto);
	        return "admin/staff/create_staff";
	    }

	    @PostMapping("/create_staff")
	    public String saveStaff(@Valid @ModelAttribute("staffDto") StaffDto staffDto, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "admin/staff/create_staff";
	        }
	        staffService.createStaff(staffDto);
	        return "redirect:/admin/staff";
	    }

	    
	    @GetMapping("/staff/edit/{id}")
	    public String showEditStaff(@PathVariable("id") Long id, Model model) {
	        Staff staff = staffService.getStaffById(id);
	        StaffDto staffDto = new StaffDto();
	        staffDto.setName(staff.getName());
	    	staffDto.setAddress(staff.getAddress());
	    	staffDto.setPhno(staff.getPhno());
	    	staffDto.setEmail(staff.getEmail());
	    	staffDto.setPassword(staff.getPassword());
	    	
	        model.addAttribute("staffDto" , staffDto);
	        model.addAttribute("staffId",id);
	        return "admin/staff/update_staff";
	    }

	    @PostMapping("/staff/edit/{id}")
	    public String editStaff(@PathVariable("id") Long id, @Valid @ModelAttribute("staffDto") StaffDto staffDto, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            model.addAttribute("staffid", id);
	            model.addAttribute("staffDto" , staffDto);
	            return "admin/staff/update_staff";
	        }
	        staffService.editStaff(id, staffDto);
	        return "redirect:/admin/staff";
	    }
	    


	    @GetMapping("/staff/delete/{id}")
	    public String deleteStaff(@PathVariable Long id) {
	        staffService.deleteStaff(id);
	        return "redirect:/admin/staff";
	    }
	    
	    
	    //for borrowing record
	    
	    @Autowired
	    private BorrowingRecordService borrowingRecordService;

	    @GetMapping("/borrowingRecord")
	    public String viewBorrowingRecords(Model model) {
	        List<BorrowingRecord> borrowingRecords = borrowingRecordService.getAllBorrowingRecords();
	        model.addAttribute("borrowingRecords", borrowingRecords);
	        return "admin/borrowingRecord/borrowingRecord";
	    }

	    
	    //@PathVariable Long id, Model mode
	    @GetMapping("/borrowingRecords/edit/{id}")
	    public String editBorrowingRecord(@PathVariable Long id, Model model) {
	        BorrowingRecord borrowingRecord = borrowingRecordService.getBorrowingRecordById(id);
	        model.addAttribute("borrowingRecord", borrowingRecord);
	        return "admin/borrowingRecord/updateBorrowingRecord";
	    }

	    @PostMapping("/borrowingRecords/update")
	    public String updateBorrowingRecord(
	            @RequestParam("id") Long id,
	            @RequestParam("returnDate") LocalDate returnDate,
	            @RequestParam("status") String status) {
	        BorrowingRecord borrowingRecord = borrowingRecordService.getBorrowingRecordById(id);
	        borrowingRecord.setReturnDate(returnDate);
	        borrowingRecord.setStatus(status);
	//        long fine = borrowingRecordService.calculateFine(borrowingRecord.getReturnDate(), borrowingRecord.getDueDate());
	        long fine = borrowingRecordService.calculateFine(borrowingRecord.getReturnDate(), borrowingRecord.getDueDate(), borrowingRecord.getId());
	        borrowingRecord.setFine(fine);
	        borrowingRecordService.updateBorrowingRecord(borrowingRecord);
	        return "redirect:/admin/borrowingRecord";
	    }
//	    
//	    TESTING QUANTITY
//	    
//	    @PostMapping("/borrowingRecords/update")
//	    public String updateBorrowingRecord(@ModelAttribute BorrowingRecord borrowingRecord) {
//	        BorrowingRecord existingRecord = borrowingRecordService.getBorrowingRecordById(borrowingRecord.getId());
//
//	        if ("returned".equals(borrowingRecord.getStatus()) && "pending".equals(existingRecord.getStatus())) {
//	            Book book = existingRecord.getBook();
//	            book.setQuantity(book.getQuantity() + existingRecord.getQuantityBorrowed());
//	            bookService.updateBook(book);
//	        }
//
//	        borrowingRecordService.updateBorrowingRecord(borrowingRecord);
//	        return "redirect:/admin/borrowingRecord";
//	    }

	    
	    @GetMapping("/borrowingRecords/delete/{id}")
	    public String deleteRecord(@PathVariable Long id) {
	    	
	    	borrowingRecordService.deleteBorrowingRecord(id);
	    	return "redirect:/admin/borrowingRecord";
	    }
//	    
//	    @GetMapping("/borrowingRecords/delete/{id}")
//	    public String deleteBorrowingRecord(@PathVariable("id") Long id) {
//	        BorrowingRecord borrowingRecord = borrowingRecordService.getBorrowingRecordById(id);
//	        
//	        if ("pending".equals(borrowingRecord.getStatus())) {
//	            Book book = borrowingRecord.getBook();
//	            book.setQuantity(book.getQuantity() + 1); // Increment the quantity when the book is deleted
//	            bookService.updateBook(book);
//	        }
//	        
//	        borrowingRecordService.deleteBorrowingRecord(id);
//	        return "redirect:/admin/borrowingRecord";
//	    }
	    
//	    @GetMapping("/staff/delete/{id}")
//	    public String deleteStaff(@PathVariable Long id) {
//	        staffService.deleteStaff(id);
//	        return "redirect:/staff";
//	    }
	   
	    
	

}
