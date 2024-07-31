package com.mymovies.movies.service.series;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovies.movies.entity.series.Episode;
import com.mymovies.movies.entity.series.Season;
import com.mymovies.movies.exception.NotFoundExceprion;
import com.mymovies.movies.model.request.EpisodeRquestDto;
import com.mymovies.movies.repository.EpisodeRepository;
import com.mymovies.movies.repository.SeasonRepository;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    
    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Override
    public Episode getEpisodeById(Long id) {
        Optional<Episode> optionalEpisode = episodeRepository.findById(id);
        return optionalEpisode.orElse(null);
    }

    @Override
    public Episode saveEpisode(EpisodeRquestDto episodeRquest) {
        Episode episode = new Episode();
        episode = this.changeEntity(episode, episodeRquest);
        
        return episodeRepository.save(episode);
    }

    @Override
    public Episode updateEpisode(Long id, EpisodeRquestDto episodeRquest) {
        Episode existingEpisode = this.getEpisodeById(id);
        if(existingEpisode == null) {
            throw new NotFoundExceprion("Episode for id(" + id + ") not found");
        }

        existingEpisode = this.changeEntity(existingEpisode, episodeRquest);

        return episodeRepository.save(existingEpisode);
    }

    @Override
    public void deleteEpisode(Long id) {
        episodeRepository.deleteById(id);
    }
    
    @Override
    public Episode getEpisodeBySeasonId(Long id, Long seasonId) {
        Season season = seasonRepository.findById(seasonId).orElse(null);
        if(season == null) {
            return null;
        }
        List<Episode> episodes = episodeRepository.findBySeasonAndId(season, id);
        if(episodes.isEmpty()) {
            return null;
        }

        return episodes.getFirst();
    }

    @Override
    public List<Episode> getEpisodesBySeasonId(Long seasonId) {
        Season season = seasonRepository.findById(seasonId).orElse(null);
        if(season == null) {
            return null;
        }

        return episodeRepository.findBySeasonOrderByNumberAsc(season);
    }

    private Episode changeEntity(Episode episode, EpisodeRquestDto episodeRquest) {
        episode.setImg(episodeRquest.getImg());
        episode.setNumber(episodeRquest.getNumber());
        episode.setNameRu(episodeRquest.getNameRu());
        episode.setNameEng(episodeRquest.getNameEng());
        episode.setReleaseDateEng(episodeRquest.getReleaseDateEng());
        episode.setReleaseDateRu(episodeRquest.getReleaseDateRu());
        episode.setRatingIMDb(episodeRquest.getRatingIMDb());
        episode.setDescription(episodeRquest.getDescription());

        Long seasonId = episodeRquest.getSeason().getId();
        Season findSeason = seasonRepository.findById(seasonId).orElse(null);
        if(findSeason == null) {
            throw new NotFoundExceprion("Season for id(" + seasonId + ") not found");
        }

        episode.setSeason(findSeason);

        return episode;
    }
}
