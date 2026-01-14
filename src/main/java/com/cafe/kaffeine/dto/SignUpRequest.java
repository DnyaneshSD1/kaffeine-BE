package com.cafe.kaffeine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    private String fullName;
    private String username;  // email used as username
    private String password;  // bcrypt hash
    private String role;
}
