package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.security.filters;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.blueweb.springboot.diamond_project.backend.diamond_project_backend.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    private AuthenticationManager authenticationManager;
    
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
    User user = null;
    String usuario = null;
    String password = null;

    try {
        user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        usuario = user.getUsuario();
        password = user.getPass();
    } catch (IOException e) {
        throw new RuntimeException("Failed to parse authentication request", e);
    }

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, password);

    Authentication result = authenticationManager.authenticate(authenticationToken);
    return result;
}


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
                org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        
        String username = user.getUsername();

        Claims claims = Jwts.claims() // adding roles to claims
                .add("usuario", username)
        .build();

        
        String token = Jwts.builder()
                .subject(username)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();

        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);


        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("usuario", username);
        body.put("message", String.format("Welcome %s you have successfully logged in!", username));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(200);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
                Map<String, String> body = new HashMap<>();

            body.put("message", "Authentication error username or password is incorrect!");
            body.put("error", failed.getMessage());
    
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(401);
            response.setContentType(CONTENT_TYPE);
    }
    
    
}
