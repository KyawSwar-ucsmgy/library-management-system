package com.spring_security_one.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring_security_one.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

		@Query("SELECT r FROM Role r LEFT JOIN FETCH r.users where r.name = :name")
		Optional<Role> findByName( String name);
}
