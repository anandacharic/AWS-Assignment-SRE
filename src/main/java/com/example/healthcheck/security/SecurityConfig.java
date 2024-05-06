package com.example.healthcheck.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.springframework.security.config.http.MatcherType.mvc;

@Configuration
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource ){

        InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager();

        try {
            Connection connection= dataSource.getConnection();
            if(connection!=null) {
                PreparedStatement preparedStatement = connection.prepareStatement("select email as username, password, 1 as active from account");
                ResultSet resultSet=preparedStatement.executeQuery();

                while(resultSet.next()){

                    UserDetails user= User.builder()
                            .username(resultSet.getString("username"))
                            .password(resultSet.getString("password"))
                            .build();
                    inMemoryUserDetailsManager.createUser(user);
                }
            }
        }catch (Exception e){

        }
        return inMemoryUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests((a)-> {
                    a.requestMatchers("/v1/assignments/**").authenticated();
                })
                .csrf(csrf-> csrf.disable())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/healthz").requestMatchers("/testing");
    }



}
