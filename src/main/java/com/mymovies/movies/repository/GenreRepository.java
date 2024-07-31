package com.mymovies.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovies.movies.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}