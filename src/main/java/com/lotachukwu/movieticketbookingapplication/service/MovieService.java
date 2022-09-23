package com.lotachukwu.movieticketbookingapplication.service;


import com.lotachukwu.movieticketbookingapplication.dto.request.CreateMovieRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateMovieResponse;

public interface MovieService {


    CreateMovieResponse createMovie(CreateMovieRequest createMovieRequest);

    void deleteAll();
}
