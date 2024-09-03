package com.spring_security_one.demo.model;



import java.time.LocalDate;

public class BorrowingRecordDto {
    
	
	    private int userId;
	    private int bookId;
	    private LocalDate startDate;
	    private LocalDate dueDate;
	    private LocalDate returnDate;
	    private String status;
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getBookId() {
			return bookId;
		}
		public void setBookId(int bookId) {
			this.bookId = bookId;
		}
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public LocalDate getDueDate() {
			return dueDate;
		}
		public void setDueDate(LocalDate dueDate) {
			this.dueDate = dueDate;
		}
		public LocalDate getReturnDate() {
			return returnDate;
		}
		public void setReturnDate(LocalDate returnDate) {
			this.returnDate = returnDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public BorrowingRecordDto(int userId, int bookId, LocalDate startDate, LocalDate dueDate, LocalDate returnDate,
				String status) {
			super();
			this.userId = userId;
			this.bookId = bookId;
			this.startDate = startDate;
			this.dueDate = dueDate;
			this.returnDate = returnDate;
			this.status = status;
		}
		public BorrowingRecordDto() {
			super();
		}

	    
    
}

