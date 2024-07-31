package com.mymovies.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovies.movies.entity.series.Episode;
import com.mymovies.movies.entity.series.Season;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {

    List<Episode> findBySeasonAndId(Season season, Long id);
    List<Episode> findBySeasonOrderByNumberAsc(Season season);
}