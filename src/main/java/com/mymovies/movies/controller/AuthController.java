package com.mymovies.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymovies.movies.model.dto.LoginDto;
import com.mymovies.movies.model.request.SignupRequest;
import com.mymovies.movies.model.response.JwtAuthResponse;
import com.mymovies.movies.model.response.MessageResponse;
import com.mymovies.movies.service.auth.AuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> 
    	addUser(@RequestBody SignupRequest signupRequest) {
        
        authService.registerUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }

}
