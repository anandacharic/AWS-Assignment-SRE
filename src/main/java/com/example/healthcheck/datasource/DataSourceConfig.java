package com.example.healthcheck.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Configuration
public class DataSourceConfig {
    @Value("${app.url}")
    String url;
    @Value("${app.username}")
    String username;
    @Value("${app.password}")
    String password;
    @Value("${app.database}")
    String database;
    @Bean
    public DataSource dataSource() {
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            String sql = "CREATE DATABASE "+database;
            statement.executeUpdate(sql);
        }catch (Exception e){

        }
        return DataSourceBuilder.create().url(url+database)
                .username(username)
                .password(password)
                .build();
    }
}
