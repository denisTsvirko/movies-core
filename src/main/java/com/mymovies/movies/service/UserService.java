package com.mymovies.movies.service;

import java.util.List;

import com.mymovies.movies.entity.auth.User;

public interface UserService {
    User getUserById(Long id);
    void deleteUser(Long id);
    List<User> getAllUsers();
}
