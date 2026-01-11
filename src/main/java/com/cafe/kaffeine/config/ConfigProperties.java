package com.cafe.kaffeine.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("config")
public class ConfigProperties {

    @Value("${com.cafe.kaffeine.cors.allow-origins}")
    private String corsOrigins;

    @PostConstruct
    public void init() {
        Configurations.CORS_ORIGINS = corsOrigins;
    }
}

