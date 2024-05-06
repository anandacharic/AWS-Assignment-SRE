package com.example.healthcheck.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
public class healthCheck {
    @Value("${app.url}")
    String url;
    @Value("${app.username}")
    String username;
    @Value("${app.password}")
    String password;
    @GetMapping("/healthz")
    public ResponseEntity<String> getConnection(HttpServletRequest request) {
        String value = request.getQueryString();
        if (request.getContentLength() < 0 && value == null) {
            try {
                DriverManager.getConnection(url, username, password);
                return ResponseEntity
                        .ok()
                        .cacheControl(CacheControl.noCache())
                        .build();
            } catch (SQLException e) {
                return ResponseEntity
                        .status(HttpStatus.SERVICE_UNAVAILABLE)
                        .cacheControl(CacheControl.noCache())
                        .build();
            }
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .cacheControl(CacheControl.noCache())
                    .build();
        }
    }

    @RequestMapping(value = "/healthz**",method = {RequestMethod.DELETE,RequestMethod.HEAD,RequestMethod.POST,RequestMethod.OPTIONS,RequestMethod.PATCH,RequestMethod.PUT,RequestMethod.TRACE})
    public ResponseEntity<String> sendErrorMessage(HttpServletResponse responses){
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .cacheControl(CacheControl.noCache())
                .build();
    }

    @RequestMapping(value = "/v1/assignments",method = {RequestMethod.DELETE,RequestMethod.PATCH,RequestMethod.PUT})
    public ResponseEntity<String> sendErorMessage(){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .cacheControl(CacheControl.noCache())
                .build();
    }

    @RequestMapping(value = "/v1/assignments/**",method = {RequestMethod.PATCH})
    public ResponseEntity<String> sendEorMessage(){
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .cacheControl(CacheControl.noCache())
                .build();
    }


}
