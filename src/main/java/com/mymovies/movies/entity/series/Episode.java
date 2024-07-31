package com.mymovies.movies.entity.series;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "episodes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "episodes", "season"})
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @Column(name = "number")
    @ColumnDefault("1")
    private Integer number = 1;

    @Column(name = "img")
    private String img;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_eng", nullable = false)
    private String nameEng;

    @Column(name = "release_date_ru")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate releaseDateRu;

    @Column(name = "release_date_eng")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate releaseDateEng;

    @Column(name = "rating_IMDb")
    @ColumnDefault("0.0")
    private float ratingIMDb;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Episode() {}

    public Episode(
        Season season,
        String nameRu,
        String nameEng,
        LocalDate releaseDateRu,
        LocalDate releaseDateEng,
        float ratingIMDb,
        String description,
        Integer number
    ) {
        this.season = season;
        this.nameRu = nameRu;
        this.nameEng = nameEng;
        this.releaseDateRu = releaseDateRu;
        this.releaseDateEng = releaseDateEng;
        this.ratingIMDb = ratingIMDb;
        this.description = description;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
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

    public LocalDate getReleaseDateRu() {
        return releaseDateRu;
    }

    public void setReleaseDateRu(LocalDate releaseDateRu) {
        this.releaseDateRu = releaseDateRu;
    }

    public LocalDate getReleaseDateEng() {
        return releaseDateEng;
    }

    public void setReleaseDateEng(LocalDate releaseDateEng) {
        this.releaseDateEng = releaseDateEng;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}