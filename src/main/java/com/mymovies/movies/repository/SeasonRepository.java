package com.mymovies.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovies.movies.entity.series.Season;
import com.mymovies.movies.entity.series.Series;

public interface SeasonRepository extends JpaRepository<Season, Long> {

    List<Season> findBySeriesAndId(Series series, Long id);
    List<Season> findBySeriesOrderByNumberAsc(Series series);
}