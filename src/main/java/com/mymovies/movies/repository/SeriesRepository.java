package com.mymovies.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovies.movies.entity.series.Series;

public interface SeriesRepository extends JpaRepository<Series, Long> {
}