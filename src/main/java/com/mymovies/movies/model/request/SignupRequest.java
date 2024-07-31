package com.mymovies.movies.model.request;

import java.util.List;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
	private String email;
	private String name;
	private List<String> roles;
	private String password;
}
