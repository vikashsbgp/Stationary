package com.vikash.stationary.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikash.stationary.entities.Order;
import com.vikash.stationary.entities.Product;
import com.vikash.stationary.entities.ProductStatus;
import com.vikash.stationary.entities.Status;
import com.vikash.stationary.repos.OrderRepository;
import com.vikash.stationary.repos.ProductStatusRepository;


public class OrderServiceImpl implements OrderService {
	
	@Autowired
	ProductStatusRepository productStatusRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductStatusService productStatusService;

	@Override
	public Map<Status, HashSet<Product>> getProductStatusMap(Long order_id) {
		
		Map<Status, HashSet<Product>> productMap = new HashMap<>();
		Order order = orderRepository.findById(order_id).get();
		List<ProductStatus> prodStats = productStatusRepository.findAllByOrderId(order.getId());
		
		for (ProductStatus obj : prodStats) {
			
			Status status = obj.getStatus();
			productMap.put(status, productStatusService.findAllProductByStatus(status));
			
		}
		
		return productMap;
	}

	@Override
	public void setProductStatusMap(Order order, Map<Status, HashSet<Product>> productStatus) {
		
		productStatusRepository.deleteAllByOrderId(order.getId());
		ProductStatus obj = new ProductStatus();
		
		for (Entry<Status, HashSet<Product>> entry : productStatus.entrySet()) {
			
			for (Product prod : entry.getValue()) {
				
				obj.setOrder(order);
				obj.setStatus(entry.getKey());
				obj.setProduct(prod);
				productStatusRepository.save(obj);
				
			}
			
		}
		
		
		
	}
	
	

}
