package com.mymovies.movies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mymovies.movies.entity.Genre;
import com.mymovies.movies.exception.NotFoundExceprion;
import com.mymovies.movies.model.request.GenreRquestDto;
import com.mymovies.movies.repository.GenreRepository;

@Service
public class GenreService implements GenreServiceInterface {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre getGenreById(Long id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        return optionalGenre.orElse(null);
    }

    @Override
    public Genre saveGenre(GenreRquestDto genreRquest) {
        Genre genre = new Genre(genreRquest.getName());
        genre.setIsActive(genreRquest.getIsActive());
        
        return genreRepository.save(genre);
    }

    @Override
    public Genre updateGenre(Long id, GenreRquestDto genreRquest) {
        Genre existingGenre = this.getGenreById(id);
        if(existingGenre == null) {
            throw new NotFoundExceprion("Genre for id(" + id + ") not found");
        }

        existingGenre.setName(genreRquest.getName());
        existingGenre.setIsActive(genreRquest.getIsActive());

        return genreRepository.save(existingGenre);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
