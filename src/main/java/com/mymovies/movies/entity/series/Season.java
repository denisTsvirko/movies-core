package com.mymovies.movies.entity.series;

import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import com.mymovies.movies.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "seasons")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "series_id", nullable = false)
    private Series series;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    @ColumnDefault("1")
    private Status status = Status.CREATED;
    
    @Column(name = "year")
    private Integer year;

    @Column(name = "number")
    @ColumnDefault("1")
    private Integer number = 1;

    @Column(name = "img")
    private String img;

    @Column(name = "total_episodes")
    @ColumnDefault("0")
    private Integer totalEpisodes = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "season", cascade = CascadeType.ALL)
    private Set<Episode> episodes;

    public Season() {}

    public Season(
        Series series, 
        Status status,  
        Integer year, 
        Integer number,
        String img, 
        Integer totalEpisodes
    ) {
        this.series = series;
        this.status = status;
        this.year = year;
        this.number = number;
        this.img = img;
        this.totalEpisodes = totalEpisodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(Integer totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public Set<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Set<Episode> episodes) {
        this.episodes = episodes;
    }
}
