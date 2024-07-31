package com.mymovies.movies.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mymovies.movies.exception.BadRequestException;
import com.mymovies.movies.exception.NotFoundExceprion;
import com.mymovies.movies.model.exception.ErrorResponse;
import com.mymovies.movies.model.exception.ResultResponse;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundExceprion.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundExceprion e) {
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)        
        .body(ErrorResponse.builder()
                    .result(ResultResponse.builder()
                            .code("404")
                            .message(e.getMessage())
                            .build())
                    .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final BadRequestException e) {
        return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)        
        .body(ErrorResponse.builder()
                    .result(ResultResponse.builder()
                            .code("400")
                            .message(e.getMessage())
                            .build())
                    .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(ErrorResponse.builder()
                    .result(ResultResponse.builder()
                            .code("422")
                            .message(e.getMessage())
                            .build())
        .build());
     }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
        .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        
        return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(ErrorResponse.builder()
                    .result(ResultResponse.builder()
                            .code("422")
                            .message(ex.getMessage())
                            .errors(errors)
                            .build())
        .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(DataIntegrityViolationException ex) {
        return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(ErrorResponse.builder()
                    .result(ResultResponse.builder()
                            .code("422")
                            .message(ex.getMessage())
                            .build())
        .build());
    }
}
