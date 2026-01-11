package com.cafe.kaffeine.service.impl;

import com.cafe.kaffeine.service.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class BaseImpl implements Base {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void addRecords() {
        mongoTemplate.createCollection("demo1");
    }
}
