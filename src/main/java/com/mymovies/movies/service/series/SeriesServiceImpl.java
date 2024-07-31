package com.mymovies.movies.service.series;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mymovies.movies.entity.Genre;
import com.mymovies.movies.entity.series.Series;
import com.mymovies.movies.exception.NotFoundExceprion;
import com.mymovies.movies.model.request.SeriesRquestDto;
import com.mymovies.movies.repository.GenreRepository;
import com.mymovies.movies.repository.SeriesRepository;


@Service
public class SeriesServiceImpl implements SeriesService {
    
    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private GenreRepository  genreRepository;

    @Override
    public Series getSeriesById(Long id) {
        Optional<Series> optionalSeries = seriesRepository.findById(id);
        return optionalSeries.orElse(null);
    }

    @Override
    @Transactional
    public Series saveSeries(SeriesRquestDto seriesRquest) {
        
        Series series = new Series();
        series = this.changeEntity(series, seriesRquest);

        return seriesRepository.save(series);
    }

    @Override
    public Series updateSeries(Long id, SeriesRquestDto seriesRquest) {
        Series existingSeries = this.getSeriesById(id);
        if(existingSeries == null) {
            throw new NotFoundExceprion("Series for id(" + id + ") not found");
        }

        existingSeries = this.changeEntity(existingSeries, seriesRquest);
        return seriesRepository.save(existingSeries);
    }

    @Override
    public void deleteSeries(Long id) {
        seriesRepository.deleteById(id);
    }

    @Override
    public List<Series> getAllSeries() {
        return seriesRepository.findAll();
    }

    private Series changeEntity(Series series, SeriesRquestDto seriesRquest) {
        series.setImg(seriesRquest.getImg());
        series.setNameRu(seriesRquest.getNameRu());
        series.setNameEng(seriesRquest.getNameEng());
        series.setStatus(seriesRquest.getStatus());
        series.setPremiereDate(seriesRquest.getPremiereDate());
        series.setCountry(seriesRquest.getCountry());
        series.setRatingIMDb(seriesRquest.getRatingIMDb());
        series.setDescription(seriesRquest.getDescription());
        series.setStory(seriesRquest.getStory());


        Set<Genre> genres = new HashSet<>();
        for (Genre genre:seriesRquest.getGenres()) {
            Genre findGenre = genreRepository.findById(genre.getId()).orElse(null);
            if(findGenre != null) {
                genres.add(findGenre);
            }
        }
        series.setGenres(genres);

        return series;
    }
}
