package com.cafe.kaffeine.controller;

import com.cafe.kaffeine.config.JwtUtil;
import com.cafe.kaffeine.dto.LoginRequest;
import com.cafe.kaffeine.dto.LoginResponse;
import com.cafe.kaffeine.dto.SignUpRequest;
import com.cafe.kaffeine.entities.LoginDetails;
import com.cafe.kaffeine.repository.LoginDetailsRepository;
import com.cafe.kaffeine.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController extends BaseController{

    @Autowired
    SignUpService service;

    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public MainController(LoginDetailsRepository repo, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        request.setPassword(encoder.encode(request.getPassword()));
        service.signUp(request);
        return created("Signup successful!");
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginDetails> login(@RequestBody LoginRequest request) {
//        try {
//            return ResponseEntity.ok(service.login(request));
//        } catch (Exception e) {
//            return ResponseEntity.status(401).build();
//        }
//    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        LoginDetails user = service.login(req);
        System.out.println(user.getPassword());
        System.out.println(encoder.encode(req.getPassword()));
        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return new LoginResponse(user.getUsername(), user.getRole(), token);
    }

}
