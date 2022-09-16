package com.lotachukwu.movieticketbookingapplication.service;

import com.lotachukwu.movieticketbookingapplication.dto.request.CreateMovieRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateMovieResponse;
import com.lotachukwu.movieticketbookingapplication.model.Movie;
import com.lotachukwu.movieticketbookingapplication.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;
    @Override
    public CreateMovieResponse createMovie(CreateMovieRequest createMovieRequest) {

        Movie movie = new Movie();
        movie.setCity(createMovieRequest.getCity());
        movie.setGenre(createMovieRequest.getGenre());
        movie.setLanguage(createMovieRequest.getLanguage());
        movie.setTitle(createMovieRequest.getTitle());

        Movie savedMovie = movieRepository.save(movie);

        CreateMovieResponse response = new CreateMovieResponse();
        response.setCity(savedMovie.getCity());
        response.setGenre(savedMovie.getGenre());
        response.setLanguage(savedMovie.getLanguage());
        response.setTitle(savedMovie.getTitle());

        return response;
    }
}
