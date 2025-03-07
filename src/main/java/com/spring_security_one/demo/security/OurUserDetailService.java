package com.spring_security_one.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring_security_one.demo.model.User;
import com.spring_security_one.demo.repository.UserRepository;


@Service
public class OurUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user=userRepository.findByEmail(email);
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("user not found ");
		}

		return new LoginUserDetail(user.get());
	}

}
