package com.mymovies.movies.service.series;

import java.util.List;

import com.mymovies.movies.entity.series.Series;
import com.mymovies.movies.model.request.SeriesRquestDto;

public interface SeriesService {
    Series getSeriesById(Long id);
    Series saveSeries(SeriesRquestDto seriesRquest);
    Series updateSeries(Long id, SeriesRquestDto seriesRquest);
    void deleteSeries(Long id);

    List<Series> getAllSeries();
}
