package com.mymovies.movies.service.auth;

import com.mymovies.movies.model.dto.LoginDto;
import com.mymovies.movies.model.request.SignupRequest;

public interface AuthService {
    String login(LoginDto loginDto);
    void registerUser(SignupRequest signupRequest);
}