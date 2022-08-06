package com.vbs.vbs.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.crypto.SecretKey;
@Configuration
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class JwtSecretKey {
    @Autowired
    private final JwtConfig jwtConfig;

    @Bean
    public SecretKey secretKey(){
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }
}
