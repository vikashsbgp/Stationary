package com.vikash.stationary.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vikash.stationary.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

}
