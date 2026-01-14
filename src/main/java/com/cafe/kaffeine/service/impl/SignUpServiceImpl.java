package com.cafe.kaffeine.service.impl;

import com.cafe.kaffeine.dao.SignInDao;
import com.cafe.kaffeine.dto.LoginRequest;
import com.cafe.kaffeine.dto.SignUpRequest;
import com.cafe.kaffeine.entities.LoginDetails;
import com.cafe.kaffeine.manager.converter.SignUpConverter;
import com.cafe.kaffeine.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    SignInDao dao;

    @Autowired
    SignUpConverter converter;

    @Override
    public void signUp(SignUpRequest request) {
        dao.signUp(converter.convertDtoToEntity(request));
    }

    @Override
    public LoginDetails login(LoginRequest request) {
        return dao.login(request.getUsername());
    }
}
