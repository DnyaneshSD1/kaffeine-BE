package com.cafe.kaffeine.dao.impl;

import com.cafe.kaffeine.dao.SignInDao;
import com.cafe.kaffeine.entities.LoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class SignInDaoImpl implements SignInDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void signUp(LoginDetails loginDetails) {
        mongoTemplate.save(loginDetails);
    }

    @Override
    public LoginDetails login(String username) {
        Query query = new Query().addCriteria(
                Criteria.where("username").is(username)
        );
        return mongoTemplate.find(query, LoginDetails.class).get(0);
    }
}
