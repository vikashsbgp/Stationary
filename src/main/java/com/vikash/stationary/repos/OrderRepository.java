package com.vikash.stationary.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vikash.stationary.entities.Order;
import com.vikash.stationary.entities.User;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

}
