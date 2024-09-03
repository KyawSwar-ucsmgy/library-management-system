package com.spring_security_one.demo.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;



import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		
		private String user_name;
		private String password;
		private String email;
		private Date created_at;
		private Date updated_at;
		
		@Column (nullable = false)
		private boolean enabled;
		
		@PrePersist
		private void onCreate() {
			this.enabled = true;
		}
		
//		@ManyToMany(mappedBy = "users", fetch =FetchType.EAGER)		
//		private Set<Role> roles = new HashSet<>();
//		
		@ManyToMany(fetch =FetchType.EAGER)
		@JoinTable(
				name = "user_role",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id")
				)
		private Set<Role> roles = new HashSet<>();
		
		
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
		    private Set<BorrowingRecord> borrowingRecords = new HashSet<>();

		public User(int id, String user_name, String password, String email, Date created_at, Date updated_at,
				boolean enabled, Set<Role> roles) {
			this.id = id;
			this.user_name = user_name;
			this.password = password;
			this.email = email;
			this.created_at = created_at;
			this.updated_at = updated_at;
			this.enabled = enabled;
			this.roles = roles;
		}

		
		
		public User(int id, String user_name, String password, String email, Date created_at, Date updated_at,
				boolean enabled, Set<Role> roles, Set<BorrowingRecord> borrowingRecords) {
			super();
			this.id = id;
			this.user_name = user_name;
			this.password = password;
			this.email = email;
			this.created_at = created_at;
			this.updated_at = updated_at;
			this.enabled = enabled;
			this.roles = roles;
			this.borrowingRecords = borrowingRecords;
		}



		public Set<BorrowingRecord> getBorrowingRecords() {
			return borrowingRecords;
		}



		public void setBorrowingRecords(Set<BorrowingRecord> borrowingRecords) {
			this.borrowingRecords = borrowingRecords;
		}



		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Date getCreated_at() {
			return created_at;
		}

		public void setCreated_at(Date created_at) {
			this.created_at = created_at;
		}

		public Date getUpdated_at() {
			return updated_at;
		}

		public void setUpdated_at(Date updated_at) {
			this.updated_at = updated_at;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public Set<Role> getRoles() {
			return roles;
		}

		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}

		public User() {
		}
		
}
