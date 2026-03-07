package com.example.Post.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String path = request.getRequestURI();

        // Debug Log
        System.out.println("DEBUG: Request received on path: " + path);

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            System.out.println("DEBUG: Bearer token found. Attempting validation...");

            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractEmail(token);
                System.out.println("DEBUG: Token valid. User: " + email);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        email, null, new ArrayList<>());

                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                System.out.println("DEBUG: Token validation failed for path: " + path);
            }
        } else {
            System.out.println("DEBUG: No 'Bearer' Authorization header found for path: " + path);
        }

        filterChain.doFilter(request, response);
    }
}