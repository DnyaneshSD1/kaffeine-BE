package com.cafe.kaffeine.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "login_details")
public class LoginDetails {
    @Id
    private String id;
    private String fullName;
    private String username;  // email used as username
    private String password;  // bcrypt hash
    private String role;      // OWNER / CUSTOMER
}
