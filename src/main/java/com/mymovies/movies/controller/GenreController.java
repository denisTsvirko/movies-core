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

import com.mymovies.movies.entity.Genre;
import com.mymovies.movies.model.request.GenreRquestDto;
import com.mymovies.movies.service.GenreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/genres")
@Validated
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Long id) {
        return genreService.getGenreById(id);
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping
    public ResponseEntity<Genre> addGenre(@RequestBody @Valid GenreRquestDto genreRquest) {
      return new ResponseEntity<>(genreService.saveGenre(genreRquest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody @Valid GenreRquestDto genreRquest) {
        return new ResponseEntity<>(genreService.updateGenre(id, genreRquest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }
}
