package com.mymovies.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovies.movies.entity.auth.User;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByUsernameOrEmail(String username, String email);
}
