package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {
		public UserModel findByUsername(String username);
}
