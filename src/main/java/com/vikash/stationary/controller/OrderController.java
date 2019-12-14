package com.vikash.stationary.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vikash.stationary.entities.Order;
import com.vikash.stationary.entities.Status;
import com.vikash.stationary.entities.OrderViewModel;
import com.vikash.stationary.entities.Product;
import com.vikash.stationary.entities.User;
import com.vikash.stationary.repos.OrderRepository;
import com.vikash.stationary.repos.UserRepository;
import com.vikash.stationary.services.OrderService;

@RestController
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderService orderService;

	@PostMapping("/order/place")
	@ResponseBody
	public Order placeOrders(@RequestBody Order order) {
		LOGGER.info("Inside placeOrders parameters = " + order);
		orderRepository.save(order);
		return order;
	}

	@PostMapping("/order/edit")
	@ResponseBody
	public Order editOrders(@RequestBody OrderViewModel orderViewModel) {
		
		LOGGER.info("Inside editOrders parameter = " + orderViewModel);
		Order order = orderRepository.findById(orderViewModel.getId()).get();
		LOGGER.info("Getting order by id");
		Map<Status, HashSet<Product>> productMap = orderService.getProductStatusMap(order.getId());
		HashSet<Product> productListStatus = productMap.get(orderViewModel.getPrevStatus());
		for (Product product : productListStatus) {
			if (product.getId() == orderViewModel.getProduct().getId()) {
				productListStatus.remove(product);
			}
		}
		productMap.put(orderViewModel.getPrevStatus(), productListStatus);

		orderViewModel.getProduct();

		HashSet<Product> prod = productMap.get(orderViewModel.getStatus());
		if (prod == null) {
			prod = new HashSet<>();
		}
		prod.add(orderViewModel.getProduct());
		productMap.put(orderViewModel.getStatus(), prod);
		orderService.setProductStatusMap(order, productMap);
		orderRepository.save(order);
		return order;
	}

	@GetMapping("/order")
	public List<Order> getOrders() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		User user = userRepository.findByEmail(username);
		return orderRepository.findByUserId(user.getId());

	}
	
	@GetMapping("/cancelorders")
	public List<Product> getCancelledOrders() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		User user = userRepository.findByEmail(username);
		List<Order> orders = orderRepository.findByUserId(user.getId());
		List<Product> results = new ArrayList<>();
		for (Order order : orders) {
			Map<Status, HashSet<Product>> productMap = orderService.getProductStatusMap(order.getId());
			if (productMap.containsKey(Status.Cancelled))
				results.addAll(productMap.get(Status.Cancelled));
		}
		return results;
	}
	
	@GetMapping("/openorders")
	public List<Product> getOpenOrders() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		User user = userRepository.findByEmail(username);
		List<Order> orders = orderRepository.findByUserId(user.getId());
		List<Product> results = new ArrayList<>();
		for (Order order : orders) {
			Map<Status, HashSet<Product>> productMap = orderService.getProductStatusMap(order.getId());
			if (productMap.containsKey(Status.Placed))
				results.addAll(productMap.get(Status.Placed));
		}
		return results;
	}
	
}
