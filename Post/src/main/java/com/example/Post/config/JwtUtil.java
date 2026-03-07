package com.example.Post.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration:86400000}")
    private long expiration;

    private Key getSigningKey() {
    
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractEmail(String token) {
        Claims claims = Jwts.parserBuilder()
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
        } catch (SecurityException | MalformedJwtException e) {
            System.err.println("CRITICAL: Invalid JWT signature/format: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("CRITICAL: JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("CRITICAL: JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("CRITICAL: JWT claims string is empty: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("CRITICAL: Unexpected error during JWT validation: " + e.getClass().getName());
        }
        return false;
    }
}