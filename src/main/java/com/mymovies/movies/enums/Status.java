package com.mymovies.movies.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Status {
    CREATED(1),
    COMING(2),
    WILL_BE_ANNOUNCED(3),
    FINISHED(4);
    
    private final Integer code;

    public static Status byCode(Integer statusCode) {
        return Arrays.stream(values())
            .filter(gender -> gender.getCode().equals(statusCode))
            .findFirst()
            .orElse(null);
    }
}
