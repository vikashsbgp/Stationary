package com.vikash.stationary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@Autowired
	private BCryptPasswordEncoder encoder;

	@PostMapping("/user")
	@ResponseBody
	public User registerUser(@RequestBody User user) {
		if(user.getEmail()!=null && user.getPassword()!=null) {
			user.setPassword(encoder.encode(user.getPassword()));
			user=userRepository.save(user);
		}
		else {
			return null;
		}
		return user;	
	}
	
	@GetMapping("/getUser")
	public List<User> getUser(){
		return userRepository.findAll();
	}

	@GetMapping("/getUser/{email:.+}/")
	public User getUserEmail(@PathVariable("email") String email){
		return userRepository.findByEmail(email);
	}
	
}
