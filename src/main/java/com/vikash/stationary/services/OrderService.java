package com.vikash.stationary.services;

import java.util.List;

import com.vikash.stationary.entities.Order;
import com.vikash.stationary.entities.User;

public interface OrderService {
	
	public List<Order> findAllOrderByUser(User user);

}
