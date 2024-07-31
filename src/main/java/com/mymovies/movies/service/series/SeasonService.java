package com.mymovies.movies.service.series;

import java.util.List;

import com.mymovies.movies.entity.series.Season;
import com.mymovies.movies.model.request.SeasonRquestDto;

public interface SeasonService {
    Season getSeasonById(Long id);
    Season saveSeason(SeasonRquestDto seasonRquest);
    Season updateSeason(Long id, SeasonRquestDto seasonRquest);
    void deleteSeason(Long id);

    Season getSeasonBySeriesId(Long id, Long seriesId);
    List<Season> getSeasonsBySeriesId(Long seriesId);
}
