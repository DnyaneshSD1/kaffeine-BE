package com.cafe.kaffeine.repository;

import com.cafe.kaffeine.entities.LoginDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginDetailsRepository extends MongoRepository<LoginDetails, String> {
    Optional<LoginDetails> findByUsername(String username);
}

