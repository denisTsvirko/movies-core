package com.mymovies.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymovies.movies.entity.series.Season;
import com.mymovies.movies.entity.series.Series;
import com.mymovies.movies.model.request.SeriesRquestDto;
import com.mymovies.movies.service.series.SeasonService;
import com.mymovies.movies.service.series.SeriesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/series")
@Validated
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private SeasonService seasonService;

    @GetMapping("/{id}")
    public Series getSeriesById(@PathVariable Long id) {
        return seriesService.getSeriesById(id);
    }

    @GetMapping
    public List<Series> getAllSeries() {
        return seriesService.getAllSeries();
    }

    @GetMapping("/{id}/seasons/{seasonId}")
    public Season getSeasonBySeriesId(@PathVariable Long id, @PathVariable Long seasonId) {
        return seasonService.getSeasonBySeriesId(seasonId, id);
    }

    @GetMapping("/{id}/seasons")
    public List<Season> getSeasonsBySeries(@PathVariable Long id) {
        return seasonService.getSeasonsBySeriesId(id);
    }

    @PostMapping
    public ResponseEntity<Series> addSeries(@RequestBody @Valid SeriesRquestDto seriesRquest) {
      return new ResponseEntity<>(seriesService.saveSeries(seriesRquest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Series> updateSeason(@PathVariable Long id, @RequestBody @Valid SeriesRquestDto seriesRquest) {
        return new ResponseEntity<>(seriesService.updateSeries(id, seriesRquest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteSeries(@PathVariable Long id) {
        seriesService.deleteSeries(id);
    }
}
