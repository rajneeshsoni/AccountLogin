package com.poc.accountLogin.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poc.accountLogin.model.Users;
import com.poc.accountLogin.repository.UserRepository;
import com.poc.accountLogin.util.ApiConstants;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService{
	
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
		
		Users data = null;
		data = userRepo.findByUsername(username);
		System.out.println("test" + data);
		if(data != null){
			return new User(data.getUsername(), data.getPassword(), new ArrayList<>()); 
		}else {		
			System.out.println("test" + data);
			throw new BadCredentialsException(ApiConstants.USER_NOT_FOUND);
		}
	}
	
	
	

}
