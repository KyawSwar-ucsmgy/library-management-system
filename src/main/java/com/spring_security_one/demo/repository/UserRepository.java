package com.spring_security_one.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_security_one.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
//	Optional<User> findByEmail(String email);
    Optional<User> findByEmail(String email);

}
