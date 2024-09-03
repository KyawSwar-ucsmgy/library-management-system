package com.spring_security_one.demo.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Role {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	String name;
	Date created_time;
	
//	@ManyToMany(fetch =FetchType.EAGER)
//	@JoinTable(
//			name = "user_role",
//			joinColumns = @JoinColumn(name = "user_id"),
//			inverseJoinColumns = @JoinColumn(name = "role_id")
//			)
//	private Set<User> users = new HashSet<>();
	
	@ManyToMany(mappedBy = "roles", fetch =FetchType.EAGER)		
	private Set<User> users = new HashSet<>();
	

	public Role(int id, String name, Date created_time, Set<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.created_time = created_time;
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role() {
		super();
	}
}
