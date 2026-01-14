package com.cafe.kaffeine.dao;

import com.cafe.kaffeine.entities.LoginDetails;
import org.springframework.stereotype.Component;

@Component
public interface SignInDao {

    void signUp(LoginDetails loginDetails);

    LoginDetails login(String username);
}
