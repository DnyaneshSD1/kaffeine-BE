package com.cafe.kaffeine.manager.converter;

import com.cafe.kaffeine.dto.SignUpRequest;
import com.cafe.kaffeine.entities.LoginDetails;
import com.cafe.kaffeine.manager.DtoEntityConverter;
import org.springframework.stereotype.Component;

@Component
public class SignUpConverter implements DtoEntityConverter<SignUpRequest, LoginDetails> {
    @Override
    public LoginDetails convertDtoToEntity(SignUpRequest dto) {
        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setUsername(dto.getUsername());
        loginDetails.setFullName(dto.getFullName());
        loginDetails.setPassword(dto.getPassword());
        loginDetails.setRole(dto.getRole());
        return loginDetails;
    }

    @Override
    public SignUpRequest convertEntityToDto(LoginDetails entity) {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFullName(entity.getFullName());
        signUpRequest.setUsername(entity.getUsername());
        signUpRequest.setPassword(entity.getPassword());
        signUpRequest.setRole(entity.getRole());
        return signUpRequest;
    }
}
