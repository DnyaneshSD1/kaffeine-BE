package com.cafe.kaffeine.controller;

import com.cafe.kaffeine.service.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @Autowired
    Base base;

    @PostMapping("/demo")
    public ResponseEntity<String> demo() {
        base.addRecords();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
