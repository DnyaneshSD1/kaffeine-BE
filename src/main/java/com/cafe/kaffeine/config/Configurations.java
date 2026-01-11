package com.cafe.kaffeine.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Configurations {

    public static String CORS_ORIGINS;

    public static Set<String> getCORSAllowOrigin() {
        return new HashSet<>(Arrays.asList(CORS_ORIGINS.split(",")));
    }
}

