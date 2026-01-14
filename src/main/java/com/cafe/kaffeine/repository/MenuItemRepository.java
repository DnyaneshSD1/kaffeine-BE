package com.cafe.kaffeine.repository;

import com.cafe.kaffeine.entities.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends MongoRepository<MenuItem, String> {
}
