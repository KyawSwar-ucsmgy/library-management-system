package com.spring_security_one.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring_security_one.demo.service.RoleService;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {
	
	@Autowired
	private  RoleService roleService;
	
	public DataInitializer(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostConstruct
	public void init() throws Exception {
		roleService.initializeRoles(Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
	}
}
