package com.vikash.stationary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikash.stationary.entities.Product;
import com.vikash.stationary.repos.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public Product updateProduct(Product product) {
		product=productRepository.findById(product.getId()).get();
		if(product!=null) {
			productRepository.save(product);
			return product;
		}
		else {
			return null;
		}
		
	}
	
	

}
