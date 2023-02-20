package com.restaurant.warehouse.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

@Configuration
@ConditionalOnProperty(value="spring.datasource.enabled",havingValue = "false")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.main")
    public HikariDataSource hikariDataSource(){
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }
}
