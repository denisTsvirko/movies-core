package com.mymovies.movies.model.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse {

    private String code;
    private String message;
    private List<String> errors;
}
