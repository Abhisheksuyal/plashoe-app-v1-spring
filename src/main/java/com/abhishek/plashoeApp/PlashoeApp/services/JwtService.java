package com.abhishek.plashoeApp.PlashoeApp.services;


import com.abhishek.plashoeApp.PlashoeApp.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final Logger log = LoggerFactory.getLogger(JwtService.class);
    @Value("${jwt.key}")
    private String key;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(UserEntity userEntity){
        log.info("inside jwt generation");
           return Jwts.builder().subject(userEntity.getId().toString())
                   .claim("email",userEntity.getEmail())
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis() + 1000*60*60))
                   .signWith(getSecretKey())
                   .compact();
    }

    public Long getIdFromToken(String token){
        Claims claims = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
        return Long.valueOf(claims.getSubject());
    }


}
