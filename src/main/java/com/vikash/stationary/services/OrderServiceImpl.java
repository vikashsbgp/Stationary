package com.vikash.stationary.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikash.stationary.entities.Order;
import com.vikash.stationary.entities.User;
import com.vikash.stationary.repos.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<Order> findAllOrderByUser(User user) {
		
		List<Order> orders = orderRepository.findAll();
		
		List<Order> result = new ArrayList<>();
		
		for (Order obj: orders) {
			
			if (obj.getUser().getId() == user.getId())
				result.add(obj);
			
		}
		return result;
	}

}
