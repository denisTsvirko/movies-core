package com.mymovies.movies.service;

import java.util.List;

import com.mymovies.movies.entity.Genre;
import com.mymovies.movies.model.request.GenreRquestDto;

public interface GenreServiceInterface {
    Genre getGenreById(Long id);
    Genre saveGenre(GenreRquestDto genreRquest);
    Genre updateGenre(Long id, GenreRquestDto genreRquest);
    void deleteGenre(Long id);

    List<Genre> getAllGenres();
}
