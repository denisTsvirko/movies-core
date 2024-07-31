package com.mymovies.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovies.movies.entity.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(String name);
}
