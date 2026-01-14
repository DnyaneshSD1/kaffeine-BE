package com.cafe.kaffeine.service;

import com.cafe.kaffeine.dto.LoginRequest;
import com.cafe.kaffeine.dto.SignUpRequest;
import com.cafe.kaffeine.entities.LoginDetails;
import org.springframework.stereotype.Service;

@Service
public interface SignUpService {
    void signUp(SignUpRequest request);

    LoginDetails login(LoginRequest request);
}
