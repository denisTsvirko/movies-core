package com.mymovies.movies.entity.series;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mymovies.movies.entity.Genre;
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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "series")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "seasons"})
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img")
    private String img;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_eng", nullable = false)
    private String nameEng;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    @ColumnDefault("1")
    private Status status = Status.CREATED;

    @Column(name = "premiere_date")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate premiereDate;

    @Column(name = "country")
    private String country;

    @Column(name = "rating_imdb")
    @ColumnDefault("0.0")
    private float ratingIMDb;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String story;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
        })
    @JoinTable(name = "series_genres",
        joinColumns = { @JoinColumn(name = "series_id") },
        inverseJoinColumns = { @JoinColumn(name = "genre_id") })
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "series", cascade = CascadeType.ALL, orphanRemoval= true)
    private List<Season> seasons;

    public Series() {}

    public Series(
        String img, 
        String nameRu,
        String nameEng,
        Status status,
        LocalDate premiereDate,
        String country,
        float ratingIMDb,
        String description,
        String story
    ) {
        this.img = img;
        this.nameRu = nameRu;
        this.nameEng = nameEng;
        this.status = status;
        this.premiereDate = premiereDate;
        this.country = country;
        this.ratingIMDb = ratingIMDb;
        this.description = description;
        this.story = story;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(LocalDate premiereDate) {
        this.premiereDate = premiereDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getRatingIMDb() {
        return ratingIMDb;
    }

    public void setRatingIMDb(float ratingIMDb) {
        this.ratingIMDb = ratingIMDb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }
}
