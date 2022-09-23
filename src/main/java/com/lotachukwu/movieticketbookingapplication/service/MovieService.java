package com.lotachukwu.movieticketbookingapplication.service;


import com.lotachukwu.movieticketbookingapplication.dto.request.CreateMovieRequest;
import com.lotachukwu.movieticketbookingapplication.dto.request.SearchMovieRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateMovieResponse;
import com.lotachukwu.movieticketbookingapplication.model.Movie;

import java.util.List;

public interface MovieService {


    CreateMovieResponse createMovie(CreateMovieRequest createMovieRequest);

    void deleteAll();

    List<Movie> searchMovie(String searchRequest);
}
