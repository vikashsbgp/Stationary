package com.vikash.stationary.services;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vikash.stationary.entities.Product;
import com.vikash.stationary.entities.ProductStatus;
import com.vikash.stationary.entities.Status;
import com.vikash.stationary.repos.ProductStatusRepository;

public class ProductStatusServiceImpl implements ProductStatusService {

	@Autowired
	ProductStatusRepository productStatusRepository;
	
	@Override
	public HashSet<Product> findAllProductByStatus(Status status) {
		
		HashSet<Product> result = new HashSet<Product>();
		List<ProductStatus> products = productStatusRepository.findAllByStatus(status);
		
		for (ProductStatus obj : products) {
			result.add(obj.getProduct());
		}
		
		return result;
	}

}
