package com.spring_security_one.demo.service;

import java.util.List;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring_security_one.demo.model.Role;
import com.spring_security_one.demo.repository.RoleRepository;

import jakarta.transaction.Transactional;

@Service
public class RoleServiceImpl implements RoleService{
	
	private static final Logger Logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private final RoleRepository roleRepository;

	@Override
	@Transactional
	public void initializeRoles(List<String> roles) throws Exception {
		for (String roleName : roles) {
			
			try {
				if (roleRepository.findByName(roleName).isEmpty()) {
					Role role = new Role();
					role.setName(roleName);
					roleRepository.save(role);
					Logger.info("Role {} has been inserted.", roleName);
				} else {
					Logger.info("Role {} already exists", roleName);
				}
			} catch (DataAccessException e) {
				Logger.error("Database access error while inserting role {}: {}", roleName, e.getMessage());
				throw new Exception("Error while accessing the database for role: " + roleName, e);
			}  catch (Exception e) {
				Logger.error("Unexpected error while inserting role {}: {}", roleName, e.getMessage());
				throw new Exception ("Unexpected error for role " + roleName, e);
			}
		}
		
	}

	public RoleServiceImpl(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

}
