package com.cafe.kaffeine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected <T> ResponseEntity<T> created(T t) {
        return new ResponseEntity<T>(t, HttpStatus.CREATED);
    }

    protected <T> ResponseEntity<T> get(T t) {
        return new ResponseEntity<T>(t, HttpStatus.OK);
    }

    protected <T> ResponseEntity<T> updated(T t) {
        return new ResponseEntity<T>(t, HttpStatus.OK);
    }

    protected <T> ResponseEntity<?> getNoContentResponse(T t) {
        return new ResponseEntity<>(t, HttpStatus.NO_CONTENT);
    }

    protected <T> ResponseEntity<?> getOKResponse(T t) {
        return new ResponseEntity<T>(t, HttpStatus.OK);
    }
}
