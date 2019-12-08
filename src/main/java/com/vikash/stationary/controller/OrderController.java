package com.vikash.stationary.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vikash.stationary.entities.Order;
import com.vikash.stationary.entities.OrderViewModel;
import com.vikash.stationary.entities.Order.Status;
import com.vikash.stationary.entities.Product;
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
	
	@PostMapping("/order/place")
	@ResponseBody
	public Order placeOrders(@RequestBody Order order) {
		orderRepository.save(order);
		return order;
	}
	
	@PostMapping("/order/edit")
	@ResponseBody
	public Order editOrders(@RequestBody OrderViewModel orderViewModel) {
		Order order = orderRepository.findById(orderViewModel.getId()).get();
		Map<Status, HashSet<Product>> productMap = order.getProducts();
		HashSet<Product> productListStatus = productMap.get(orderViewModel.getPrevStatus());
		for(Product product:productListStatus) {
			if(product.getId()==orderViewModel.getProduct().getId()) {
				productListStatus.remove(product);
			}
		}
		productMap.put(orderViewModel.getPrevStatus(), productListStatus);
		productMap.put(orderViewModel.getStatus(), orderViewModel.getProduct());

		orderViewModel.getProduct();
		/*for (Entry<Status, HashSet<Product>> productListStatus : productMap.entrySet()) {
			if(productListStatus.getValue().contains(orderViewModel.getProduct())) {
				productListStatus.getValue().remove(orderViewModel.getProduct());
				productListStatus1 = productListStatus.getValue();
			}
		}*/
		//productMap.put(orderViewModel.getStatus(), productListStatus1);

		HashSet<Product> prod = productMap.get(orderViewModel.getStatus());
		if (prod == null) {
			prod = new HashSet<>();
		}
		prod.add(orderViewModel.getProduct());
		productMap.put(orderViewModel.getStatus(), prod);
		order.setProducts(productMap);
		orderRepository.save(order);
		return order;
	}
	
	@GetMapping("/order")
	public List<Order> getOrders(@RequestBody User user){
		
		return orderService.findAllOrderByUser(user);
		
	}
}
