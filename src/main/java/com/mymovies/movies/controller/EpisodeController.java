package com.mymovies.movies.controller;

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
import com.mymovies.movies.model.request.EpisodeRquestDto;
import com.mymovies.movies.service.series.EpisodeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/episodes")
@Validated
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    @GetMapping("/{id}")
    public Episode getEpisodeById(@PathVariable Long id) {
        return episodeService.getEpisodeById(id);
    }

    @PostMapping
    public ResponseEntity<Episode> addEpisode(@RequestBody @Valid EpisodeRquestDto episodeRquest) {
      return new ResponseEntity<>(episodeService.saveEpisode(episodeRquest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Episode> updateEpisode(@PathVariable Long id, @RequestBody @Valid EpisodeRquestDto episodeRquest) {
        return new ResponseEntity<>(episodeService.updateEpisode(id, episodeRquest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteEpisode(@PathVariable Long id) {
        episodeService.deleteEpisode(id);
    }
}
