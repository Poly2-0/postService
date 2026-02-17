package com.example.Post.config;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
}
    public String extractEmail(String token) {
       Claims claims=Jwts.parserBuilder()
                     .setSigningKey(getSigningKey())
                     .build()
                     .parseClaimsJws(token)
                     .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                          .setSigningKey(getSigningKey())
                          .build()
                          .parseClaimsJws(token);

                return true;
        } catch (Exception e) {
            return false;
        }
    }
}
