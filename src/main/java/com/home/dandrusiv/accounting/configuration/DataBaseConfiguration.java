package com.home.dandrusiv.accounting.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.home.dandrusiv.accounting.repositories")
@PropertySource("classpath:dataSource.properties")
public class DataBaseConfiguration {
}