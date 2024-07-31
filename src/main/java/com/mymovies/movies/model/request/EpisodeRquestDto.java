package com.mymovies.movies.model.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mymovies.movies.entity.series.Season;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeRquestDto {
    private Season season;

    @Min(1)
    private Integer number;

    
    @NotBlank(message = "Invalid img: Empty img")
    private String img;

    @Size(min = 3, max = 255, message = "Invalid nameRu: Must be of 3 - 255 characters")
    String nameRu;

    @NotBlank(message = "Invalid nameEng: Empty nameEng")
    @NotNull(message = "Invalid nameEng: nameEng is NULL")
    @Size(min = 3, max = 255, message = "Invalid nameEng: Must be of 3 - 255 characters")
    String nameEng;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate releaseDateRu;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate releaseDateEng;

    @Min(0)
    @NotNull(message = "Invalid ratingIMDb: ratingIMDb is NULL")
    float ratingIMDb;

    @NotBlank(message = "Invalid nameEng: Empty nameEng")
    String description;
}
