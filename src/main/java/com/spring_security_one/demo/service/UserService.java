package com.spring_security_one.demo.service;


import java.util.List;

import com.spring_security_one.demo.model.User;

public interface UserService {
	
	//User getUserById(int id);
	User getUserByEmail(String email);
    User getUserById(Integer id);
    List<User> getAllUsers();
    User saveUser(User user);
  //  User updateUser(Integer id, User user);
 //   void deleteUser(Integer id);

}
