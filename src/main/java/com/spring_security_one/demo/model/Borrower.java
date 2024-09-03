package com.spring_security_one.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="borrowers")
public class Borrower {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 
	 	private Long id;
	    private String name;
	    private String email;
	    private String address;
	    private String password;
	    private String phone;
//	    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, orphanRemoval = true)
//	    private Set<BorrowingRecord> borrowingRecords = new HashSet<>();
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public Borrower(Long id, String name, String email, String address, String password, String phone) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.address = address;
			this.password = password;
			this.phone = phone;
		}
		public Borrower() {
			super();
		}

	    
	

}
