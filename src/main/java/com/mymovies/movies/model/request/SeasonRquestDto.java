package com.mymovies.movies.model.request;


import com.mymovies.movies.entity.series.Series;
import com.mymovies.movies.enums.Status;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeasonRquestDto {

    private Series series;

    @NotNull(message = "Invalid status: status is NULL")
    private Status status;

    @Min(1970)
    private Integer year;

    @Min(1)
    private Integer number;

    @NotBlank(message = "Invalid img: Empty img")
    private String img;

    @Min(0)
    private Integer totalEpisodes;
}
