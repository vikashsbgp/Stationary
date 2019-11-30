package com.vikash.stationary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vikash.stationary.entities.Order;
import com.vikash.stationary.entities.User;
import com.vikash.stationary.repos.OrderRepository;
import com.vikash.stationary.repos.UserRepository;
import com.vikash.stationary.services.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/order")
	@ResponseBody
	public Order placeOrders(@RequestBody Order order) {
		orderRepository.save(order);
		return order;
	}
	
	@GetMapping("/order")
	public List<Order> getOrders(@RequestBody User user){
		
		return orderService.findAllOrderByUser(user);
		
	}
}
