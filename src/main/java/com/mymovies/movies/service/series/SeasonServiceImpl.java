package com.mymovies.movies.service.series;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovies.movies.entity.series.Season;
import com.mymovies.movies.entity.series.Series;
import com.mymovies.movies.exception.NotFoundExceprion;
import com.mymovies.movies.model.request.SeasonRquestDto;
import com.mymovies.movies.repository.SeasonRepository;
import com.mymovies.movies.repository.SeriesRepository;

@Service
public class SeasonServiceImpl implements SeasonService {
    
    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Override
    public  Season getSeasonById(Long id) {
        Optional<Season> optionalSeason = seasonRepository.findById(id);
        return optionalSeason.orElse(null);
    }

    @Override
    public Season saveSeason(SeasonRquestDto seasonRquest) {
        Season season = new Season();
        season = this.changeEntity(season, seasonRquest);

        return seasonRepository.save(season);
    }

    @Override
    public Season updateSeason(Long id, SeasonRquestDto seasonRquest) {
        Season existingSeason = this.getSeasonById(id);
        if(existingSeason == null) {
            throw new NotFoundExceprion("Season for id(" + id + ") not found");
        }

        existingSeason = this.changeEntity(existingSeason, seasonRquest);

        return seasonRepository.save(existingSeason);
    }

    @Override
    public void deleteSeason(Long id) {
        seasonRepository.deleteById(id);
    }

    @Override
    public Season getSeasonBySeriesId(Long id, Long seriesId) {
        Series series = seriesRepository.findById(seriesId).orElse(null);
        if(series == null) {
            return null;
        }
        List<Season> seasons = seasonRepository.findBySeriesAndId(series, id);
        if(seasons.isEmpty()) {
            return null;
        }

        return seasons.getFirst();
    }

    @Override
    public List<Season> getSeasonsBySeriesId(Long seriesId) {
        Series series = seriesRepository.findById(seriesId).orElse(null);
        if(series == null) {
            return null;
        }

        return seasonRepository.findBySeriesOrderByNumberAsc(series);
    }

    private Season changeEntity(Season season, SeasonRquestDto seasonRquest) {
        season.setImg(seasonRquest.getImg());
        season.setNumber(seasonRquest.getNumber());
        season.setStatus(seasonRquest.getStatus());
        season.setYear(seasonRquest.getYear());
        season.setTotalEpisodes(seasonRquest.getTotalEpisodes());

        Long seriesId = seasonRquest.getSeries().getId();
        Series findSeries= seriesRepository.findById(seriesId).orElse(null);
        if(findSeries == null) {
            throw new NotFoundExceprion("Series for id(" + seriesId + ") not found");
        }

        season.setSeries(findSeries);

        return season;
    }
}
