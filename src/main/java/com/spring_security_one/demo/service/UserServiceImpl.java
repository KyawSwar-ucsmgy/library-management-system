package com.spring_security_one.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_security_one.demo.model.User;
import com.spring_security_one.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

//    @Override
//    public User updateUser(Integer id, User user) {
//        User existingUser = userRepository.findById(id).orElse(null);
//        if (existingUser != null) {
//            existingUser.setName(user.getName());
//            existingUser.setEmail(user.getEmail());
//            existingUser.setPassword(user.getPassword());
//            existingUser.setRoles(user.getRoles());
//            return userRepository.save(existingUser);
//        }
//        return null;
//    }

//    @Override
//    public void deleteUser(Integer id) {
//        userRepository.deleteById(id);
//    }
}
