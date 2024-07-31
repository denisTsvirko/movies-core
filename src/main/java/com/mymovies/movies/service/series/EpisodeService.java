package com.mymovies.movies.service.series;

import java.util.List;

import com.mymovies.movies.entity.series.Episode;
import com.mymovies.movies.model.request.EpisodeRquestDto;

public interface EpisodeService {
    Episode getEpisodeById(Long id);
    Episode saveEpisode(EpisodeRquestDto episodeRquest);
    Episode updateEpisode(Long id, EpisodeRquestDto episodeRquest);
    void deleteEpisode(Long id);

    Episode getEpisodeBySeasonId(Long id, Long seasonId);
    List<Episode> getEpisodesBySeasonId(Long seasonId);
}
