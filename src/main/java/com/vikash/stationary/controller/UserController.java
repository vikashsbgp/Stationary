package com.vikash.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vikash.stationary.entities.User;
import com.vikash.stationary.repos.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;

	@PostMapping("/user")
	@ResponseBody
	public User registerUser(@RequestBody User user) {
		if(user.getEmail()!=null && user.getPassword()!=null) {
			user=userRepository.save(user);
		}
		else {
			return null;
		}
		return user;	
	}
	
	
}
