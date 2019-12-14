package com.vikash.stationary.services;

import java.util.HashSet;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.vikash.stationary.entities.Order;
import com.vikash.stationary.entities.Product;
import com.vikash.stationary.entities.Status;

@Service
public interface OrderService {
	
	Map<Status, HashSet<Product>> getProductStatusMap(Long order_id);
	
	void setProductStatusMap(Order order, Map<Status, HashSet<Product>> productStatus);

}
