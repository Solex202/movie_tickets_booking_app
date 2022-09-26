package com.lotachukwu.movieticketbookingapplication.dto.response;

import com.lotachukwu.movieticketbookingapplication.model.Movie;
import lombok.Data;

import java.util.List;

@Data
public class SearchMovieResponse {

    private List<Movie> movies;

    private int noOfTotalPages;

    private int pageSize;

    private int currentPage;

    private int noOfMovies;
}
