package com.myproject.blog.Controller;

import java.sql.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.myproject.blog.Entity.User;
import com.myproject.blog.Security.AuthRequest;
import com.myproject.blog.Security.AuthResponse;
import com.myproject.blog.Security.JwtTokenUtil;
 

 
@RestController
@RequestMapping("/auth")
public class AuthApi {
    @Autowired AuthenticationManager authManager;
    @Autowired JwtTokenUtil jwtUtil;

     
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );
             
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken,new Date(System.currentTimeMillis() + JwtTokenUtil.getExpireDuration()));
             
            return ResponseEntity.ok().body(response);
             
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}