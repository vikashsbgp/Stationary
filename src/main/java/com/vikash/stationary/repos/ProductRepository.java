package com.vikash.stationary.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.vikash.stationary.entities.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {
	
	@Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
	public List<Product> findByNameRegex(String name);

}
