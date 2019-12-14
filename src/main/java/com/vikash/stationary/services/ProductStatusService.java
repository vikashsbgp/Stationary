package com.vikash.stationary.services;

import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.vikash.stationary.entities.Product;
import com.vikash.stationary.entities.Status;

@Service
public interface ProductStatusService {
	
	HashSet<Product> findAllProductByStatus(Status status);

}
