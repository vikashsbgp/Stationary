package com.vikash.stationary.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikash.stationary.entities.Order;
import com.vikash.stationary.entities.Order.Status;
import com.vikash.stationary.entities.Product;
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
			
			if (obj.getUserId() == user.getId())
				result.add(obj);
			
		}
		return result;
	}


}
