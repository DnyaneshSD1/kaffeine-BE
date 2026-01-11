package com.cafe.kaffeine.config;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.io.IOException;

@Configuration
public class MongoDBConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String schemaName;

    @Bean
    @Primary
    public MongoTemplate mongoTemplate(MongoClient mongoClient) throws IOException {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient, schemaName));
    }
}