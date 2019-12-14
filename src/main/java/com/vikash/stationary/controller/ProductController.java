package com.vikash.stationary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vikash.stationary.entities.Product;
import com.vikash.stationary.repos.ProductRepository;
import com.vikash.stationary.services.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/products")
	@ResponseBody
	public List<Product> addProducts(@RequestBody List<Product> product) {
		
		List<Product> result = productRepository.saveAll(product);
		
		return result;
		
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
		
	}
	
	
	@GetMapping("/search/{filter}")
	public List<Product> getSearchedProducts(@PathVariable("filter") String name) {
		
		return productRepository.findByNameRegex(name);
		
	}
	
	@PutMapping("/edit")
	public Product editProduct(Product product) {
		product=productService.updateProduct(product);
		return product;
		
	}

}
