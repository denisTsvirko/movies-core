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

import com.mymovies.movies.entity.series.Episode;
import com.mymovies.movies.entity.series.Season;
import com.mymovies.movies.model.request.SeasonRquestDto;
import com.mymovies.movies.service.series.EpisodeService;
import com.mymovies.movies.service.series.SeasonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/seasons")
@Validated
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private EpisodeService episodeService;

    @GetMapping("/{id}")
    public Season getSeasonById(@PathVariable Long id) {
        return seasonService.getSeasonById(id);
    }

    @PostMapping
    public ResponseEntity<Season> addSeason(@RequestBody @Valid SeasonRquestDto seasonRquest) {
      return new ResponseEntity<>(seasonService.saveSeason(seasonRquest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Season> updateSeason(@PathVariable Long id, @RequestBody @Valid SeasonRquestDto seasonRquest) {
        return new ResponseEntity<>(seasonService.updateSeason(id, seasonRquest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteSeason(@PathVariable Long id) {
        seasonService.deleteSeason(id);
    }

    @GetMapping("/{id}/episodes/{episodeId}")
    public Episode getEpisodeBySeriesId(@PathVariable Long id, @PathVariable Long episodeId) {
        return episodeService.getEpisodeBySeasonId(episodeId, id);
    }

    @GetMapping("/{id}/episodes")
    public List<Episode> getEpisodesBySeasonId(@PathVariable Long id) {
        return episodeService.getEpisodesBySeasonId(id);
    }
}
